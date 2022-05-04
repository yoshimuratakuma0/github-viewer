package com.free.domain.usecases

import com.free.core.Result
import com.free.domain.di.UsersRepositoryAnnotation
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    @UsersRepositoryAnnotation
    private val repository: UsersRepository
) {
    suspend fun execute(params: GetUserDetailInputParams): Result<UserDetail> {
        return repository.userDetail(params)
    }
}

/**
 * @param username: unique userID
 */
class GetUserDetailInputParams(val username: String)