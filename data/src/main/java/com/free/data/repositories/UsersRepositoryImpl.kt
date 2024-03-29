package com.free.data.repositories

import com.free.data.datasources.GithubApi
import com.free.data.exceptions.from
import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UsersRepository {
    /**
     * max value of pageSize and initialLoadSize is 100
     */
    override suspend fun users(params: FetchUsersInputParams): ListingData {
        val response = api.users(
            since = params.since,
            perPage = params.perPage,
        )
        if (!response.isSuccessful) {
            throw FetchUsersException.from(response.code())
        }
        val users = response.body()!!.map { dataModel ->
            dataModel.entity
        }
        return ListingData(
            children = users,
            params = params,
        )
    }

    override fun userDetail(params: GetUserDetailInputParams): Flow<UserDetail> {
        return flow {
            val response = api.userDetail(
                username = params.username,
            )
            if (!response.isSuccessful) {
                throw FetchUsersException.from(response.code())
            }
            emit(response.body()!!.entity)
        }
    }
}