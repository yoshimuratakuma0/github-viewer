package com.free.feature_user.models

import com.free.domain.entities.UserDetail
import java.time.LocalDateTime

data class UserDetailUiModel(
    val avatarUrl: String,
    val displayName: String,
    val followers: Int,
    val following: Int,
    val companyText: String?,
    val emailText: String?,
    val bioText: String?,
    val lastActivityAt: LocalDateTime,
    val accountCreationAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(
            userDetail: UserDetail,
        ): UserDetailUiModel {
            val displayName = userDetail.name ?: userDetail.user.username
            return UserDetailUiModel(
                avatarUrl = userDetail.user.avatarUrl,
                displayName = displayName,
                followers = userDetail.followers,
                following = userDetail.following,
                companyText = userDetail.company,
                emailText = userDetail.email,
                bioText = userDetail.bio,
                lastActivityAt = userDetail.updatedAt,
                accountCreationAt = userDetail.createdAt,
            )
        }
    }
}