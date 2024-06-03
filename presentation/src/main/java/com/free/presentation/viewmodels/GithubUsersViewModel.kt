package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.free.domain.entities.User
import com.free.domain.usecases.FetchUsersUseCase
import com.free.presentation.views.GithubUsersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {
    val usersFlow: Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = 50,
            initialLoadSize = 100
        )
    ) {
        GithubUsersPagingSource(
            fetchUsersUseCase = fetchUsersUseCase
        )
    }.flow.cachedIn(viewModelScope)
}