package com.free.domain.entities

import com.free.domain.usecases.FetchUsersInputParams

class ListingData<T>(
    val children: List<User>,
    val params: T,
)

class UserListingData(
    val children: List<User>,
    val params: FetchUsersInputParams,
)