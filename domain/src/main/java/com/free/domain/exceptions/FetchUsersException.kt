package com.free.domain.exceptions

sealed class FetchUsersException(
    message: String? = null
) : Exception(message) {
    object NotModified : FetchUsersException()
    object Unauthenticated : FetchUsersException()
    object Forbidden : FetchUsersException()
    object ExceedLimit : FetchUsersException()
    object Unknown : FetchUsersException()

    companion object
}