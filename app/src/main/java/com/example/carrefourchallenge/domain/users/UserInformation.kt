package com.example.carrefourchallenge.domain.users


import com.google.gson.annotations.SerializedName

data class UserInformation(
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("hireable")
    val hireable: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int? = null,
    @SerializedName("public_gists")
    val publicGists: Int? = null,
    @SerializedName("followers")
    val followers: Int? = null,
    @SerializedName("following")
    val following: Int? = null
)