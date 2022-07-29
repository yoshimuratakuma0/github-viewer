package com.free.data.repositories

import com.free.data.datasources.GithubApi
import com.free.domain.Result
import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UsersRepository {
    /**
     * max value of pageSize and initialLoadSize is 100
     */
    override suspend fun users(params: FetchUsersInputParams): Result<ListingData> {
        api.users(params).let { result ->
            return when (result) {
                is Result.Success -> {
                    Result.Success(result.data)
                }
                is Result.Error -> {
                    result
                }
            }
        }
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail> {
        api.userDetail(params).let { result ->
            return when (result) {
                is Result.Success -> {
                    Result.Success(result.data)
                }
                is Result.Error -> {
                    result
                }
            }
        }
    }
}