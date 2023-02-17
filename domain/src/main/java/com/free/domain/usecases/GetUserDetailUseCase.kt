package com.free.domain.usecases

import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailUseCase(
    private val repository: UsersRepository
) {
    operator fun invoke(params: GetUserDetailInputParams): Flow<UserDetail> {
        return repository.userDetail(params)
    }
}

/**
 * @param username: unique userID
 */
data class GetUserDetailInputParams(val username: String)