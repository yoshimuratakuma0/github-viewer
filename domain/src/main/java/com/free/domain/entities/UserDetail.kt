package com.free.domain.entities

import java.time.LocalDateTime

class UserDetail(
    username: String,
    avatarUrl: String,
    followersUrl: String,
    followingUrl: String,
    organizationsUrl: String,
    company: String?,
    name: String?,
    followers: Int,
    following: Int,
    updatedAt: LocalDateTime,
    createdAt: LocalDateTime
) : User(username, avatarUrl, followersUrl, followingUrl, organizationsUrl)