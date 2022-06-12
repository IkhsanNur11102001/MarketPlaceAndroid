package com.nur_ikhsan.marketplace.core.data.source.remote.response

import com.nur_ikhsan.marketplace.core.data.source.model.User

data class LoginResponse(
    val code :Int? = null,
    val message : String? = null,
    val data:User? = null
)
