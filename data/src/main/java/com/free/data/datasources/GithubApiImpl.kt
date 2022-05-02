package com.free.data.datasources

import com.free.core.Result
import com.free.data.models.UserDetailModel
import com.free.data.models.UserModel
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GithubApiImpl @Inject constructor() : GithubApi {
    companion object {
        /**
         * default value of timeout is 10 sec
         */
        const val CONNECTION_TIMEOUT_MILLISECONDS = 10000L
        const val READ_TIMEOUT_MILLISECONDS = 10000L
        const val BASE_URL = "https://api.github.com/"
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun users(params: FetchUsersInputParams): Result<ListingData> {
        var url = "${BASE_URL}users?per_page=${params.perPage}"
        if (params.since != null) {
            url += "&since=${params.since}"
        }
        val request = Request.Builder()
            .url(url)
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        return suspendCoroutine {
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val models =
                            json.decodeFromString<List<UserModel>>(
                                response.body?.string() ?: ""
                            )
                        it.resume(
                            Result.Success(ListingData(models, params))
                        )
                    } else {
                        it.resume(
                            Result.Error(Exception())
                        )
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    it.resume(
                        Result.Error(e)
                    )
                }
            })
        }
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetailModel> {
        val request = Request.Builder()
            .url("${BASE_URL}users/${params.username}")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        return suspendCoroutine {
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val models =
                            json.decodeFromString<UserDetailModel>(
                                response.body?.string() ?: ""
                            )
                        it.resume(
                            Result.Success(models)
                        )
                    } else {
                        it.resume(
                            Result.Error(Exception())
                        )
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    it.resume(
                        Result.Error(e)
                    )
                }
            })
        }
    }
}