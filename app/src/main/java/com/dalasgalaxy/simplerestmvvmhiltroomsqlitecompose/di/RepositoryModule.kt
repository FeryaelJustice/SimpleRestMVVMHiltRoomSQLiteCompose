package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.di

import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository.SettingsRepository
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository.SettingsRepositoryImpl
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository.UserRepository
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun userRepository(repository: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun settingsRepository(settingsRepository: SettingsRepositoryImpl): SettingsRepository
}