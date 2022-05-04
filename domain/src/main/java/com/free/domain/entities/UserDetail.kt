package com.free.domain.entities

import java.time.LocalDateTime

class UserDetail(
    id: Int,
    username: String,
    avatarUrl: String,
    val company: String?,
    val name: String?,
    val email: String?,
    val bio: String?,
    val followers: Int,
    val following: Int,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) : User(id, username, avatarUrl) {
    val displayName get() : String = name ?: username
    val hasCompany get(): Boolean = company != null
    val hasEmail get(): Boolean = email != null
    val hasBio get(): Boolean = bio != null
}