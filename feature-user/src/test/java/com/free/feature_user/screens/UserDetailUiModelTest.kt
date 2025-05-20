package com.free.feature_user.screens

import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.feature_user.models.UserDetailUiModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class UserDetailUiModelTest {
    @Test
    fun return_displayName_when_no_name() {
        // Given
        val userDetail = UserDetail(
            user = User(
                id = 1,
                username = "test_username",
                avatarUrl = "https://example.com/avatar.png",
            ),
            company = "Test Company",
            name = null,
            email = "email",
            bio = "Test Bio",
            followers = 100,
            following = 50,
            updatedAt = LocalDateTime.now(),
            createdAt = LocalDateTime.now()
        )

        // When
        val userDetailUiModel = UserDetailUiModel.fromDomain(userDetail)

        // Then
        assertEquals("test_username", userDetailUiModel.displayName)
    }

    @Test
    fun return_displayName_when_name_is_not_null() {
        // Given
        val userDetail = UserDetail(
            user = User(
                id = 1,
                username = "test_username",
                avatarUrl = "https://example.com/avatar.png",
            ),
            company = "Test Company",
            name = "Test Name",
            email = "email",
            bio = "Test Bio",
            followers = 100,
            following = 50,
            updatedAt = LocalDateTime.now(),
            createdAt = LocalDateTime.now()
        )

        // When
        val userDetailUiModel = UserDetailUiModel.fromDomain(userDetail)

        // Then
        assertEquals("Test Name", userDetailUiModel.displayName)
    }
}