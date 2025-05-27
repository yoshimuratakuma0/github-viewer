package com.free.domain.usecases

import com.free.domain.annotations.IoDispatcher
import com.free.domain.entities.User
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val repository: UsersRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : CoroutineUseCase<FetchUsersInputParams, List<User>>(ioDispatcher) {
    override suspend fun execute(params: FetchUsersInputParams): List<User> {
        require(params.perPage <= 100) {
            throw FetchUsersException.ExceedLimit
        }
        return repository.users(params)
    }
}

/**
 * @param since: A user ID. Only return users with an ID greater than this ID.
 * @param perPage: Results per page (max 100)
 */
data class FetchUsersInputParams(val since: Int?, val perPage: Int)