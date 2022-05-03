package com.free.data.datasources

import com.free.core.Result
import com.free.data.models.UserDetailModel
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject

@ExperimentalSerializationApi
class GithubApiImpl @Inject constructor() : GithubApi {
    private val format = Json { ignoreUnknownKeys = true }

    private val service = Retrofit.Builder()
        .baseUrl(GithubService.BASE_URL)
        .addConverterFactory(format.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(GithubService::class.java)

    override suspend fun users(params: FetchUsersInputParams): Result<ListingData> {
        val response = service.users(params.since, params.perPage)
        if (response.isSuccessful) {
            return Result.Success(
                ListingData(
                    response.body()!!,
                    params.since,
                    params.perPage
                )
            )
        }
        throw Exception()
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetailModel> {
        val response = service.userDetail(params.username)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        throw Exception()
    }
}