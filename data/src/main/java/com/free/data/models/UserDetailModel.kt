package com.free.data.models

import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Serializable
data class UserDetailModel(
    val id: Int,
    @SerialName("login") val username: String,
    @SerialName("avatar_url") val avatarUrl: String,
    val company: String?,
    val name: String?,
    val email: String?,
    val bio: String?,
    val followers: Int,
    val following: Int,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("created_at") val createdAt: String
) {
    val entity
        get(): UserDetail = UserDetail(
            user = User(
                id = id,
                username = username,
                avatarUrl = avatarUrl
            ),
            company = company,
            name = name,
            email = email,
            bio = bio,
            followers = followers,
            following = following,
            updatedAt = LocalDateTime.parse(
                updatedAt,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.JAPANESE)
            ),
            createdAt = LocalDateTime.parse(
                createdAt,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.JAPANESE)
            ),
        )
}