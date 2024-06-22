package com.free.domain.usecases

import com.free.domain.entities.UserListingData
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher

class FetchUsersUseCase(
    private val repository: UsersRepository,
    ioDispatcher: CoroutineDispatcher,
) : CoroutineUseCase<FetchUsersInputParams, UserListingData>(ioDispatcher) {
    override suspend fun execute(params: FetchUsersInputParams): UserListingData {
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