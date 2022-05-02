package com.free.data.datasources

import com.free.core.Result
import com.free.data.models.UserDetailModel
import com.free.data.models.UserModel
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams

class ListingData(
    val children: List<UserModel>,
    val params: FetchUsersInputParams
)

interface GithubApi {
    suspend fun users(params: FetchUsersInputParams): Result<ListingData>
    suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetailModel>
}