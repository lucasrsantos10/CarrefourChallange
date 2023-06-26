package com.example.carrefourchallenge.domain.users

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("login")
    val name: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)
