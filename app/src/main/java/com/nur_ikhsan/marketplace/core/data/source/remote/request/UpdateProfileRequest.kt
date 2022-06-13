package com.nur_ikhsan.marketplace.core.data.source.remote.request

import java.net.IDN

data class UpdateProfileRequest(
    val id: Int,
    val name:String,
    val email:String,
    val phone:String
)


