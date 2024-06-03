package com.free.domain.repositories

import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import kotlinx.coroutines.flow.Flow


interface UsersRepository {
    suspend fun users(params: FetchUsersInputParams): ListingData
    fun userDetail(params: GetUserDetailInputParams): Flow<UserDetail>
}