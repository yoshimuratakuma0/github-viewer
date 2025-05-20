package com.free.data.datasources

import com.free.data.models.UserDetailResponse
import com.free.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users")
    suspend fun users(
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int
    ): Response<List<UserResponse>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{username}")
    suspend fun userDetail(
        @Path("username") username: String
    ): Response<UserDetailResponse>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{username}/followers")
    suspend fun followers(
        @Path("username") username: String,
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int,
    ): Response<List<UserResponse>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{username}/following")
    suspend fun following(
        @Path("username") username: String,
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int
    ): Response<List<UserResponse>>
}