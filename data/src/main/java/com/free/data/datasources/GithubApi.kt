package com.free.data.datasources

import com.free.core.Result
import com.free.data.models.UserDetailModel
import com.free.data.models.UserModel
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

class ListingData(
    val children: List<UserModel>,
    val params: FetchUsersInputParams
)

interface GithubApi {
    suspend fun users(params: FetchUsersInputParams): Result<ListingData>
    suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetailModel>
}

interface GithubService {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users")
    suspend fun users(
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int
    ): Response<List<UserModel>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{username}")
    suspend fun userDetail(
        @Path("username") username: String
    ): Response<UserDetailModel>
}