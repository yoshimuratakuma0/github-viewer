package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    fun a() {
        CoroutineScope(Job()).launch {
            val a = getUserDetailUseCase.execute(GetUserDetailInputParams("yoshimuratakuma0"))
            val b = 0
        }
    }
}