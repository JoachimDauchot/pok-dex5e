package com.example.pokdex.testRepositories

import com.example.pokdex.data.repositories.APIVersionRepository
import com.example.pokdex.model.APIVersion

class TestApiVersionRepository : APIVersionRepository {
    var apiVersion: APIVersion = APIVersion("0.0.0", false)
    override suspend fun insert(item: APIVersion) {
        apiVersion = item
    }

    override suspend fun setDownloaded(wasDownloadedFully: Boolean) {
        apiVersion.wasDownloadedFully = wasDownloadedFully
    }

    override suspend fun getVersion(): APIVersion {
        return apiVersion
    }

    override suspend fun versionIsUpToDate(): Boolean {
        return false
    }
}
