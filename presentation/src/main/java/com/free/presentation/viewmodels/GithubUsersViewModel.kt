package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.free.domain.entities.User
import com.free.domain.usecases.FetchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {
    val usersFlow: Flow<PagingData<User>> = fetchUsersUseCase
        .execute()
        .cachedIn(viewModelScope)
}