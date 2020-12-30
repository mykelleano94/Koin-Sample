package com.neds.koinsample.data.api

import com.neds.koinsample.data.model.User
import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}