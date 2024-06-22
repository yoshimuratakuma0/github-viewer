package com.free.domain.repositories

import com.free.domain.entities.ListingData
import com.free.domain.entities.UserDetail
import com.free.domain.entities.UserListingData
import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.GetUserDetailInputParams


interface UsersRepository {
    suspend fun users(params: FetchUsersInputParams): UserListingData
    suspend fun following(params: FetchFollowingInputParams): ListingData<FetchFollowingInputParams>
    suspend fun followers(params: FetchFollowersInputParams): ListingData<FetchFollowersInputParams>
    suspend fun userDetail(params: GetUserDetailInputParams): UserDetail
}