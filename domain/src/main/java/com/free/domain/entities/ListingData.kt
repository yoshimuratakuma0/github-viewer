package com.free.domain.entities

import com.free.domain.usecases.FetchUsersInputParams

class ListingData(
    val children: List<User>,
    val params: FetchUsersInputParams
)