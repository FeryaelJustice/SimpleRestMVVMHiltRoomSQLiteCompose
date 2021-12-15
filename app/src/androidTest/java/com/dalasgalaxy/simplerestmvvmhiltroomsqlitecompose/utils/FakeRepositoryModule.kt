package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.di.RepositoryModule
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.User
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun userRepository(): UserRepository = object : UserRepository {

        private val users = MutableLiveData<List<User>>(listOf())

        override suspend fun getNewUser(): User {
            val userList = users.value!!
            val newUser =
                User("Name ${userList.size}", "LastName ${userList.size}", "City", "Image")
            users.postValue(users.value?.toMutableList()?.apply { add(newUser) })
            return newUser
        }

        override suspend fun deleteUser(userToDelete: User) {
            users.postValue(users.value?.toMutableList()?.apply { remove(userToDelete) })
        }

        override fun getAllUsers(): LiveData<List<User>> = users

    }
}