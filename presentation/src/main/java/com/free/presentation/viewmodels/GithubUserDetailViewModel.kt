package com.free.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.core.Result
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class GithubUserDetailViewModel @AssistedInject constructor(
    @Assisted private val username: String,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(username: String): GithubUserDetailViewModel
    }

    private val _name = MutableLiveData<String>()
    val name get() : LiveData<String> = _name

    init {
        viewModelScope.launch {
            getUserDetailUseCase.execute(GetUserDetailInputParams(username)).let { result ->
                when (result) {
                    is Result.Error -> TODO()
                    is Result.Success -> {
                        initState(result.data)
                    }
                }
            }
        }
    }

    private fun initState(userDetail: UserDetail) {
        _name.value = userDetail.displayName
    }
}