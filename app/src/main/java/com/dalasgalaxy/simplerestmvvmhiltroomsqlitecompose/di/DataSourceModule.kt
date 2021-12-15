package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.di

import android.content.Context
import androidx.room.Room
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.settings.SettingsDao
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.UserDao
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.provider.local.DbDataSource
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.provider.remote.RestDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    @Named("BaseURL")
    fun provideBaseURL() = "https://randomuser.me/api/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseURL") baseURL: String): Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseURL).build()

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource = retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(context, DbDataSource::class.java,"database").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun settingsDao(db: DbDataSource): SettingsDao = db.settingsDao()

    @Singleton
    @Provides
    fun userDao(db: DbDataSource): UserDao = db.userDao()
}