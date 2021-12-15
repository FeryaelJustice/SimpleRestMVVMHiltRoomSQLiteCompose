package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository

import androidx.lifecycle.LiveData
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.User
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.UserDao
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.provider.remote.RestDataSource
import kotlinx.coroutines.delay
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser(): User
    suspend fun deleteUser(userToDelete: User)
    fun getAllUsers(): LiveData<List<User>>
}

class UserRepositoryImpl @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getNewUser(): User {
        delay(2000)
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserLocation().results[0].location!!
        val picture = dataSource.getUserPicture().results[0].picture!!
        val user = User(name.first, name.last, location.city, picture.thumbnail)
        userDao.insert(user)
        return user
    }

    override suspend fun deleteUser(userToDelete: User) = userDao.delete(userToDelete)

    override fun getAllUsers(): LiveData<List<User>> = userDao.getAll()
}