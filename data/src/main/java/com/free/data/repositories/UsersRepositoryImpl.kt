package com.free.data.repositories

import com.free.data.datasources.GithubApi
import com.free.data.exceptions.from
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UsersRepository {
    /**
     * max value of pageSize and initialLoadSize is 100
     */
    override suspend fun users(params: FetchUsersInputParams): List<User> {
        val response = api.users(
            since = params.since,
            perPage = params.perPage,
        )
        if (!response.isSuccessful) {
            throw FetchUsersException.from(response.code())
        }
        return response.body()!!.map { dataModel ->
            dataModel.entity
        }
    }

    override suspend fun following(params: FetchFollowingInputParams): List<User> {
        val response = api.following(
            since = params.since,
            perPage = params.perPage,
            username = params.username,
        )
        if (!response.isSuccessful) {
            throw FetchUsersException.from(response.code())
        }
        return response.body()!!.map { dataModel ->
            dataModel.entity
        }
    }

    override suspend fun followers(params: FetchFollowersInputParams): List<User> {
        val response = api.users(
            since = params.since,
            perPage = params.perPage,
        )
        if (!response.isSuccessful) {
            throw FetchUsersException.from(response.code())
        }
        return response.body()!!.map { dataModel ->
            dataModel.entity
        }
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): UserDetail {
        val response = api.userDetail(
            username = params.username,
        )
        if (!response.isSuccessful) {
            throw FetchUsersException.from(response.code())
        }
        return response.body()!!.entity
    }
}