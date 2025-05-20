package com.free.domain.entities

import java.time.LocalDateTime

data class UserDetail(
    val user: User,
    val company: String? = null,
    val name: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val followers: Int,
    val following: Int,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime,
)