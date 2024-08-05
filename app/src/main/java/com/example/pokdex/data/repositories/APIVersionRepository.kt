package com.example.pokdex.data.repositories

import android.util.Log
import com.example.pokdex.data.database.dao.APIVersionDAO
import com.example.pokdex.data.database.dbObjects.asDbObject
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.data.network.APIVersionService
import com.example.pokdex.model.APIVersion

interface APIVersionRepository {
    suspend fun insert(item: APIVersion)
    suspend fun setDownloaded(wasDownloadedFully: Boolean)
    suspend fun getVersion(): APIVersion
    suspend fun versionIsUpToDate(): Boolean
}

class PersistAPIVersionToDb(
    private val apiVersionDAO: APIVersionDAO,
    private val apiVersionService: APIVersionService,
) : APIVersionRepository {
    override suspend fun insert(item: APIVersion) {
        apiVersionDAO.insert(item.asDbObject())
    }

    override suspend fun setDownloaded(wasDownloadedFully: Boolean) {
        val apiVersion = apiVersionDAO.getAPIVersion()
        if (apiVersion != null) {
            apiVersion.wasDownloadedFully = wasDownloadedFully
            apiVersionDAO.insert(apiVersion)
        }
    }

    override suspend fun getVersion(): APIVersion {
        val apiVersion = apiVersionDAO.getAPIVersion()
        if (apiVersion != null) {
            return apiVersion.asDomainObject()
        }
        return APIVersion("0.0.0", wasDownloadedFully = false)
    }

    override suspend fun versionIsUpToDate(): Boolean {
        val currentVersion: APIVersion = getVersion()

        return try {
            val newVersion = APIVersion(apiVersionService.getAPIVersion(), wasDownloadedFully = false)
            if (currentVersion.version == newVersion.version) {
                true
            } else {
                insert(newVersion)
                false
            }
        } catch (e: Exception) {
            Log.i("API", "API is down")
            true
        }
    }
}
