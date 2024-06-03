package com.free.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<P, R>(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(params: P): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(params).let {
                    Result.Success(it)
                }
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    internal abstract suspend fun execute(params: P): R
}