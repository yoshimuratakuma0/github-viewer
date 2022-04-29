package com.free.data.repositories

import com.free.core.Result
import com.free.data.datasources.GithubClient
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val client: GithubClient
) : UsersRepository {
    override suspend fun users(params: FetchUsersInputParams): Result<List<User>> {
        client.users(params).let { result ->
            return when (result) {
                is Result.Success -> {
                    val models = result.data
                    val entities = models.map { model ->
                        model.entity
                    }
                    Result.Success(entities)
                }
                is Result.Error -> {
                    result
                }
            }
        }
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail> {
        client.userDetail(params).let {result ->
            return when (result) {
                is Result.Success -> {
                    Result.Success(result.data.entity)
                }
                is Result.Error -> {
                    result
                }
            }
        }
    }

}