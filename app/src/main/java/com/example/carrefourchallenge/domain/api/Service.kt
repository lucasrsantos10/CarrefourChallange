package com.example.carrefourchallenge.domain.api

import com.example.carrefourchallenge.domain.users.Repository
import com.example.carrefourchallenge.domain.users.UserInformation
import com.example.carrefourchallenge.domain.users.Users
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("users")
    suspend fun getUserList(): List<Users>

    @GET("users/{userName}")
    suspend fun getUserInformation(
        @Path("userName") userName: String
    ): UserInformation

    @GET("users/{userName}/repos")
    suspend fun getUserRepositories(
        @Path("userName") userName: String
    ): List<Repository>
}