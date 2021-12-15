package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.provider.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.settings.Settings
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.settings.SettingsDao
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.User
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.UserDao

@Database(entities = [User::class, Settings::class], version = 1)
abstract class DbDataSource : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun userDao(): UserDao
}