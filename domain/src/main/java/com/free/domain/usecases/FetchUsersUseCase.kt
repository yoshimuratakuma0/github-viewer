package com.free.domain.usecases

import androidx.paging.PagingData
import com.free.domain.di.UsersRepositoryAnnotation
import com.free.domain.entities.User
import com.free.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    @UsersRepositoryAnnotation
    private val repository: UsersRepository
) {
    fun execute(): Flow<PagingData<User>> {
        return repository.users()
    }
}

/**
 * @param since: A user ID. Only return users with an ID greater than this ID.
 * @param perPage: Results per page (max 100)
 */
class FetchUsersInputParams(val since: Int?, val perPage: Int)