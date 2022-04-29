package com.free.data.datasources

import com.free.core.Result
import com.free.data.models.UserModel
import com.free.domain.usecases.FetchUsersInputParams
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GithubClient {
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

    suspend fun users(params: FetchUsersInputParams): Result<List<UserModel>> {
        val request = Request.Builder()
            .url("${BASE_URL}users?since=${params.since}&per_page=${params.perPage}")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        return suspendCoroutine {
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val models =
                            Json { ignoreUnknownKeys = true }.decodeFromString<List<UserModel>>(
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