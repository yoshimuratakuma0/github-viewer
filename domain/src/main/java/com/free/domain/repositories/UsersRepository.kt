package com.free.domain.repositories

import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams


interface UsersRepository {
    suspend fun users(params: FetchUsersInputParams): ListingData
    suspend fun userDetail(params: GetUserDetailInputParams): UserDetail
}