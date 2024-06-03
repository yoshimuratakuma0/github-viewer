package com.free.data.exceptions

import com.free.domain.exceptions.FetchUsersException


fun FetchUsersException.Companion.from(statusCode: Int): FetchUsersException =
    when (statusCode) {
        304 -> FetchUsersException.NotModified
        401 -> FetchUsersException.Unauthenticated
        403 -> FetchUsersException.Forbidden
        else -> FetchUsersException.Unknown
    }