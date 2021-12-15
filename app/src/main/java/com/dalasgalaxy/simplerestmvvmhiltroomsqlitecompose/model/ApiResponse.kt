package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model

import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.UserLocation
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.UserName
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.UserPicture

data class ApiResponse (val results: List<Results> = emptyList())

data class Results(val name: UserName?, val location: UserLocation?, val picture: UserPicture?)