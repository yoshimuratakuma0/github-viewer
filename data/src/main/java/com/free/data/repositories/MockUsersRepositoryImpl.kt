package com.free.data.repositories

import android.content.Context
import com.free.core.Result
import com.free.data.models.UserModel
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@Suppress("BlockingMethodInNonBlockingContext")
class MockUsersRepositoryImpl
@Inject constructor(
    @ApplicationContext private val context: Context
) : UsersRepository {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun users(params: FetchUsersInputParams): Result<List<User>> {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("SampleUsersResponse.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = bufferedReader.readText()
        return try {
            val models = json.decodeFromString<List<UserModel>>(jsonString)
            val entities = models.map { model -> model.entity }
            Result.Success(entities)
        } catch (e: JSONException) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetail> {
        return Result.Success(
            UserDetail(
                username = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                name = "Tom Preston-Werner",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                email = null,
                bio = null,
                followers = 123456,
                following = 1234567890,
                updatedAt = LocalDateTime.ofInstant(
                    Instant.from(DateTimeFormatter.ISO_INSTANT.parse("2022-04-03T23:40:06Z")),
                    ZoneId.of(ZoneOffset.UTC.id)
                ),
                createdAt = LocalDateTime.ofInstant(
                    Instant.from(DateTimeFormatter.ISO_INSTANT.parse("2007-10-20T05:24:19Z")),
                    ZoneId.of(ZoneOffset.UTC.id)
                )
            )
        )
    }
}