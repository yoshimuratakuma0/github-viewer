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
    val followers: Int,
    val following: Int,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) : User(username, avatarUrl, followersUrl, followingUrl, organizationsUrl) {

    val displayName get() : String = name ?: username
}