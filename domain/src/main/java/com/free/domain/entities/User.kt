package com.free.domain.entities

open class User(
    val id: Int,
    val username: String,
    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val organizationsUrl: String
)