package com.free.data.datasources


import com.free.domain.Result
import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.exceptions.GithubApiException
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject


@OptIn(ExperimentalSerializationApi::class)
class GithubApiImpl @Inject constructor() : GithubApi {
    private val format = Json { ignoreUnknownKeys = true }

    private val service = Retrofit.Builder()
        .baseUrl(GithubService.BASE_URL)
        .addConverterFactory(format.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(GithubService::class.java)

    override suspend fun users(params: FetchUsersInputParams): Result<ListingData> {
        return try {
            val response = service.users(params.since, params.perPage)
            when {
                response.isSuccessful -> {
                    Result.Success(
                        ListingData(
                            response.body()?.map { model -> model.entity } ?: emptyList(),
                            params
                        )
                    )
                }
                else -> {
                    Result.Error(GithubApiException.fromStatusCode(response.code()))
                }
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail> {
        return try {
            val response = service.userDetail(params.username)
            when {
                response.isSuccessful -> {
                    Result.Success(response.body()!!.entity)
                }
                else -> {
                    Result.Error(GithubApiException.fromStatusCode(response.code()))
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}