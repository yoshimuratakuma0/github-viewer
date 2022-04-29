package com.free.domain.usecases

import com.free.core.Result
import com.free.domain.entities.User
import com.free.domain.repositories.UsersRepository

class FetchUsersUseCase(
    private val repository: UsersRepository
) {
    private fun execute(params: FetchUsersInputParams): Result<List<User>> {
        return repository.users(params)
    }
}

/**
 * @param since: A user ID. Only return users with an ID greater than this ID.
 * @param perPage: Results per page (max 100)
 */
class FetchUsersInputParams(since: Int, perPage: Int)