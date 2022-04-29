package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {

    fun a() {
        CoroutineScope(Job()).launch {
            val a = fetchUsersUseCase.execute(FetchUsersInputParams(0, 59))
            val b = 0
        }
    }
}