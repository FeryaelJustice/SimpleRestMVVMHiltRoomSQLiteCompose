package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository

import androidx.lifecycle.LiveData
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.settings.Settings
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.settings.SettingsDao
import javax.inject.Inject

interface SettingsRepository {
    suspend fun insertSettings(settings: Settings)
    suspend fun deleteSettings(settingsToDelete: Settings)
    fun getAllSettings(): LiveData<List<Settings>>
}

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDao: SettingsDao
) : SettingsRepository {

    override suspend fun insertSettings(settings: Settings) {
        settingsDao.insert(settings)
    }

    override suspend fun deleteSettings(settingsToDelete: Settings) =
        settingsDao.delete(settingsToDelete)

    override fun getAllSettings(): LiveData<List<Settings>> = settingsDao.getAll()

}