package com.free.presentation.views

import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import java.time.LocalDateTime
import javax.inject.Inject

class FakeUsersRepository @Inject constructor() : UsersRepository {
    override suspend fun users(params: FetchUsersInputParams): List<User> {
        return listOf(
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
    }

    override suspend fun following(params: FetchFollowingInputParams): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun followers(params: FetchFollowersInputParams): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): UserDetail {
        return UserDetail(
            user = User(
                id = 1,
                username = "preview name",
                avatarUrl = "https://localhost/u/1?v=4"
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