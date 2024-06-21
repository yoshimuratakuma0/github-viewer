package com.free.data.datasources

import com.free.data.models.UserDetailModel
import com.free.data.models.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
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

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{username}/followers")
    suspend fun followers(
        @Path("username") username: String,
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int,
    ): Response<List<UserModel>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("following/{username}/following")
    suspend fun following(
        @Path("username") username: String,
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int
    ): Response<List<UserModel>>
}