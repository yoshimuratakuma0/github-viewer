package com.free.data.datasources

import android.content.Context
import com.free.core.Result
import com.free.data.models.UserDetailModel
import com.free.data.models.UserModel
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class MockGithubApi @Inject constructor(
    @ApplicationContext private val context: Context
) : GithubApi {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun users(params: FetchUsersInputParams): Result<ListingData> {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("SampleUsersResponse.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = bufferedReader.readText()
        val models = json.decodeFromString<List<UserModel>>(jsonString)
        val children = models.filter { it.id > params.since ?: 0 }.subList(0, params.perPage)
        return Result.Success(
            ListingData(children, params.since, params.perPage)
        )
    }

    override suspend fun userDetail(params: GetUserDetailInputParams): Result<UserDetailModel> {
        return Result.Success(
            UserDetailModel(
                id = 0,
                username = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                name = "Tom Preston-Werner",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                email = "sample.email@gmail.com",
                bio = "bioのテスト。あああああああああああああああああ\naaaaaaaaaaaaaaaaa\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "bbbb\ncccc\ndddd\neeee\nffff\n" +
                        "aaaa",
                followers = 123456,
                following = 1234567890,
                updatedAt = "2022-04-03T23:40:06Z",
                createdAt = "2007-10-20T05:24:19Z"
            )
        )
    }
}