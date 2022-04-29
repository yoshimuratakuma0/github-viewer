package com.free.domain.usecases

import com.free.core.Result
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository

class GetUserDetailUseCase(
    private val repository: UsersRepository
) {
    private fun execute(params: GetUserDetailInputParams): Result<UserDetail> {
        return repository.userDetail(params)
    }
}

/**
 * @param username: unique userID
 */
class GetUserDetailInputParams(username: String)