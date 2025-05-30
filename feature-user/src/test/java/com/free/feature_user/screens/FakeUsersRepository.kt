package com.free.feature_user.screens

import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import javax.inject.Inject

val dummyUsers = listOf(
    User(
        id = 1,
        username = "preview name 1",
        avatarUrl = "https://localhost/u/1?v=4"
    ),
    User(
        id = 2,
        username = "preview name 2",
        avatarUrl = "https://localhost/u/1?v=4"
    ),
    User(
        id = 3,
        username = "preview name 3",
        avatarUrl = "https://localhost/u/1?v=4"
    ),
)

class FakeUsersRepository @Inject constructor() : UsersRepository {
    override suspend fun users(params: FetchUsersInputParams): List<User> {
        return dummyUsers
    }

    override suspend fun following(params: FetchFollowingInputParams): List<User> {
        return dummyUsers
    }

    override suspend fun followers(params: FetchFollowersInputParams): List<User> {
        return dummyUsers
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): UserDetail {
        return UserDetail(
            user = User(
                id = 1,
                username = "Tom Preston-Werner",
                avatarUrl = "https://api.github.com/u/1?v=4"
            ),
            email = "sample@gmail.com",
            bio = "this is bio. \n\n\n\n\n長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio\n\n\n\n\n\n\n\n\n長いbio",
            company = "preview company",
            createdAt = LocalDateTime.MIN,
            updatedAt = LocalDateTime.MAX,
            followers = 12345,
            following = 23456,
            name = "preview name"
        )
    }
}

class FakeUsersRepositoryWithEmptyUsers @Inject constructor() : UsersRepository {
    override suspend fun users(params: FetchUsersInputParams): List<User> {
        return emptyList()
    }

    override suspend fun following(params: FetchFollowingInputParams): List<User> {
        return emptyList()
    }

    override suspend fun followers(params: FetchFollowersInputParams): List<User> {
        return emptyList()
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): UserDetail {
        return UserDetail(
            user = User(
                id = 1,
                username = "Tom Preston-Werner",
                avatarUrl = "https://api.github.com/u/1?v=4"
            ),
            email = "sample@gmail.com",
            bio = "this is bio. \n\n\n\n\n長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio\n\n\n\n\n\n\n\n\n長いbio",
            company = "preview company",
            createdAt = LocalDateTime.MIN,
            updatedAt = LocalDateTime.MAX,
            followers = 12345,
            following = 23456,
            name = "preview name"
        )
    }
}

class FakeUsersRepositoryWithError @Inject constructor() : UsersRepository {
    override suspend fun users(params: FetchUsersInputParams): List<User> {
        throw Exception("Failed to fetch users")
    }

    override suspend fun following(params: FetchFollowingInputParams): List<User> {
        throw Exception("Failed to fetch following")
    }

    override suspend fun followers(params: FetchFollowersInputParams): List<User> {
        throw Exception("Failed to fetch followers")
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): UserDetail {
        throw Exception("Failed to fetch user detail")
    }
}


class FakeUsersRepositoryForPaging @Inject constructor() : UsersRepository {
    override suspend fun users(params: FetchUsersInputParams): List<User> {
        val startIndex = params.since ?: 0
        delay(500)
        val endIndex = startIndex + params.perPage
        val users = List(endIndex - startIndex) { index ->
            User(
                id = startIndex + index + 1,
                username = "preview name ${startIndex + index + 1}",
                avatarUrl = "https://localhost/u/${startIndex + index + 1}?v=4"
            )
        }
        return users
    }

    override suspend fun following(params: FetchFollowingInputParams): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun followers(params: FetchFollowersInputParams): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): UserDetail {
        TODO("Not yet implemented")
    }
}