package com.free.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.core.Result
import com.free.domain.entities.User
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {
    companion object {
        const val PER_PAGE = 40
    }

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        fetchUsers(FetchUsersInputParams(0, PER_PAGE))
    }

    private fun fetchUsers(params: FetchUsersInputParams) = viewModelScope.launch {
        fetchUsersUseCase.execute(params).let { result ->
            when (result) {
                is Result.Success -> {
                    _users.value = result.data
                }
                is Result.Error -> {
                    // todo error handling
                }
            }
        }
    }
}