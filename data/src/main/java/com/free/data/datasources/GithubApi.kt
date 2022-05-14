package com.free.data.datasources

import com.free.core.Result
import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams


interface GithubApi {
    suspend fun users(params: FetchUsersInputParams): Result<ListingData>
    suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail>
}