package com.neds.koinsample.data.repository

import com.neds.koinsample.data.api.UserApiService

class UserRepository(private val service: UserApiService) {
    suspend fun getUsers() = service.getUsers()
}