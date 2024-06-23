package com.free.domain.entities

import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchUsersInputParams

class ListingData<T>(
    val children: List<User>,
    val params: T,
)

class UserListingData(
    val children: List<User>,
    val params: FetchUsersInputParams,
)

class FollowingListingData(
    val children: List<User>,
    val params: FetchFollowingInputParams,
)

class FollowersListingData(
    val children: List<User>,
    val params: FetchFollowersInputParams,
)