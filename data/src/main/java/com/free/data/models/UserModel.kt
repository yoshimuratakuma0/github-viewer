package com.free.data.models

import com.free.domain.entities.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    @SerialName("login") val username: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("followers_url") val followersUrl: String,
    @SerialName("following_url") val followingUrl: String,
    @SerialName("organizations_url") val organizationsUrl: String
) {
    val entity
        get(): User = User(
            id = id,
            username = username,
            avatarUrl = avatarUrl,
            followersUrl = followersUrl,
            followingUrl = followingUrl,
            organizationsUrl = organizationsUrl
        )
}