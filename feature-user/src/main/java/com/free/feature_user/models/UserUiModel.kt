package com.free.feature_user.models

import com.free.domain.entities.User

data class UserUiModel(
    val id: Int,
    val username: String,
    val avatarUrl: String,
) {
    companion object {
        fun fromDomain(user: User): UserUiModel {
            return UserUiModel(
                id = user.id,
                username = user.username,
                avatarUrl = user.avatarUrl,
            )
        }
    }
}