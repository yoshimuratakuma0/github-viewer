package com.free.presentation.views

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.free.core.Result
import com.free.domain.entities.User
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import java.io.IOException

class GithubUsersPagingSource(
    private val fetchUsersUseCase: FetchUsersUseCase
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            fetchUsersUseCase.execute(
                FetchUsersInputParams(
                    since = params.key,
                    perPage = params.loadSize
                )
            ).let { result ->
                when (result) {
                    is Result.Error -> LoadResult.Error(result.exception)
                    is Result.Success -> {
                        LoadResult.Page(
                            data = result.data.children,
                            prevKey = result.data.params.since,
                            nextKey = result.data.children.lastOrNull()?.id
                        )
                    }
                }
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}