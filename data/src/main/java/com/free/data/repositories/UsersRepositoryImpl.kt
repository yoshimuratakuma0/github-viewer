package com.free.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.free.core.Result
import com.free.data.datasources.GithubApi
import com.free.data.datasources.GithubUsersPagingSource
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.GetUserDetailInputParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UsersRepository {
    override fun users(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                initialLoadSize = 100
            )
        ) {
            GithubUsersPagingSource(api = api)
        }.flow
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail> {
        api.userDetail(params).let { result ->
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