package com.free.domain.repositories

import androidx.paging.PagingData
import com.free.core.Result
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import kotlinx.coroutines.flow.Flow


interface UsersRepository {
    fun users(): Flow<PagingData<User>>
    suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail>
}