package com.free.domain.usecases

import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetUserDetailUseCase(
    private val repository: UsersRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<GetUserDetailInputParams, UserDetail>(dispatcher) {
    override suspend fun execute(params: GetUserDetailInputParams): UserDetail {
        return repository.userDetail(params)
    }
}

/**
 * @param username: unique userID
 */
data class GetUserDetailInputParams(val username: String)