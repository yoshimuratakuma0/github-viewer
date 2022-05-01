package com.free.domain.entities

import java.time.LocalDateTime

class UserDetail(
    username: String,
    avatarUrl: String,
    followersUrl: String,
    followingUrl: String,
    organizationsUrl: String,
    val company: String?,
    val name: String?,
    val email: String?,
    val bio: String?,
    val followers: Int,
    val following: Int,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) : User(username, avatarUrl, followersUrl, followingUrl, organizationsUrl) {
    val displayName get() : String = name ?: username
    val hasCompany get(): Boolean = company != null
    val hasEmail get(): Boolean = email != null
    val hasBio get(): Boolean = bio != null

    companion object {
        fun empty(): UserDetail {
            return UserDetail(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                LocalDateTime.MIN,
                LocalDateTime.MIN
            )
        }
    }
}