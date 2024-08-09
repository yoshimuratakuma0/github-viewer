package com.free.domain.repositories

import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams


interface UsersRepository {
    suspend fun users(params: FetchUsersInputParams): List<User>
    suspend fun following(params: FetchFollowingInputParams): List<User>
    suspend fun followers(params: FetchFollowersInputParams): List<User>
    suspend fun userDetail(params: GetUserDetailInputParams): UserDetail
}