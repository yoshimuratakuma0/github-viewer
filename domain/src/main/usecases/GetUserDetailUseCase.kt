package com.free.domain.usecases

import com.free.domain.Result
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository

class GetUserDetailUseCase(
    private val repository: UsersRepository
) {
    suspend fun execute(params: GetUserDetailInputParams): Result<UserDetail> {
        return repository.userDetail(params)
    }
}

/**
 * @param username: unique userID
 */
data class GetUserDetailInputParams(val username: String)