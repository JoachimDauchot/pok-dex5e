package com.example.pokdex.data.repositories

import com.example.pokdex.data.database.dao.APIVersionDAO
import com.example.pokdex.data.database.dbObjects.asDbObject
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.data.network.APIVersionService
import com.example.pokdex.model.APIVersion

interface APIVersionRepository {
    suspend fun insert(item: APIVersion)
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

    override suspend fun getVersion(): APIVersion {
        return apiVersionDAO.getAPIVersion().asDomainObject()
    }

    override suspend fun versionIsUpToDate(): Boolean {
        var currentVersion: APIVersion = getVersion()
        var newVersion = APIVersion(apiVersionService.getAPIVersion())
        if (currentVersion.equals(newVersion)) {
            return true
        } else {
            insert(newVersion)
            return false
        }
    }
}
