package com.free.domain.usecases

import com.free.domain.entities.User
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher

class FetchFollowingUseCase(
    private val repository: UsersRepository,
    ioDispatcher: CoroutineDispatcher,
) : CoroutineUseCase<FetchFollowingInputParams, List<User>>(ioDispatcher) {
    override suspend fun execute(params: FetchFollowingInputParams): List<User> {
        require(params.perPage <= 100) {
            throw FetchUsersException.ExceedLimit
        }
        return repository.following(params)
    }
}

/**
 * @param since: A user ID. Only return users with an ID greater than this ID.
 * @param perPage: Results per page (max 100)
 */
data class FetchFollowingInputParams(val since: Int?, val perPage: Int, val username: String)