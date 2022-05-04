package com.free.data.models

import com.free.domain.entities.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    @SerialName("login") val username: String,
    @SerialName("avatar_url") val avatarUrl: String
) {
    val entity
        get(): User = User(
            id = id,
            username = username,
            avatarUrl = avatarUrl
        )
}