package com.example.carrefourchallenge.domain.repositories

import android.util.Log
import com.example.carrefourchallenge.domain.api.Service
import com.example.carrefourchallenge.domain.users.Repository
import com.example.carrefourchallenge.domain.users.UserInformation
import com.example.carrefourchallenge.domain.users.Users
import javax.inject.Inject

class UsersRepository @Inject constructor(private var service: Service) {
    suspend fun getUsersList(): List<Users> {
        return service.getUserList()
    }

    suspend fun getRepositories(userName: String): List<Repository> {
        Log.i("userRepository", "started")
        return service.getUserRepositories(userName)
    }

    suspend fun getUserInfo(userName: String): UserInformation {
        Log.i("userInfo", "started")
        return service.getUserInformation(userName)
    }


}
