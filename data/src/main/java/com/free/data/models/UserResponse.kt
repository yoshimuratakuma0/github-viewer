package com.free.data.models

import com.free.domain.entities.User
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class UserResponse(
    val id: Int,
    @SerialName("login") val username: String,
    @SerialName("avatar_url") val avatarUrl: String
)

fun UserResponse.toEntity(): User {
    return User(
        id = id,
        username = username,
        avatarUrl = avatarUrl
    )
}