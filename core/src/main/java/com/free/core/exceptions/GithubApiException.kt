package com.free.core.exceptions

sealed class GithubApiException(message: String) : Exception(message) {
    class NotModifiedException(message: String) :
        GithubApiException(message)

    class UnauthenticatedException(message: String) :
        GithubApiException(message)

    class ForbiddenException(message: String) :
        GithubApiException(message)

    class UnknownException(message: String) :
        GithubApiException(message)

    companion object {
        fun fromStatusCode(statusCode: Int): GithubApiException {
            return when (statusCode) {
                304 -> NotModifiedException("not modified exception")
                401 -> UnauthenticatedException("unauthenticated exception")
                403 -> ForbiddenException("forbidden exception")
                else -> UnknownException("unknown exception")
            }
        }
    }
}