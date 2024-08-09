package com.free.domain.usecases

import com.free.domain.entities.User
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher

class FetchFollowersUseCase(
    private val repository: UsersRepository,
    ioDispatcher: CoroutineDispatcher,
) : CoroutineUseCase<FetchFollowersInputParams, List<User>>(ioDispatcher) {
    override suspend fun execute(params: FetchFollowersInputParams): List<User> {
        require(params.perPage <= 100) {
            throw FetchUsersException.ExceedLimit
        }
        return repository.followers(params)
    }
}

/**
 * @param since: A user ID. Only return users with an ID greater than this ID.
 * @param perPage: Results per page (max 100)
 */
data class FetchFollowersInputParams(val since: Int?, val perPage: Int, val username: String)