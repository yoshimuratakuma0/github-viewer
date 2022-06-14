package com.free.domain.entities

import java.time.LocalDateTime

data class UserDetail(
    val user: User,
    val company: String?,
    val name: String?,
    val email: String?,
    val bio: String?,
    val followers: Int,
    val following: Int,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) {
    val id get(): Int = user.id
    val displayName get() : String = name ?: user.username
    val hasCompany get(): Boolean = company != null
    val hasEmail get(): Boolean = email != null
    val hasBio get(): Boolean = bio != null
}