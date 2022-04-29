package com.free.domain.repositories

import com.free.core.Result
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams

interface UsersRepository {
    suspend fun users(params: FetchUsersInputParams): Result<List<User>>
    suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail>
}