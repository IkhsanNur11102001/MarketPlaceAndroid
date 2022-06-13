package com.nur_ikhsan.marketplace.core.data.source.remote

import com.nur_ikhsan.marketplace.core.data.source.remote.network.ApiService
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data:LoginRequest) = api.login(data)
    suspend fun regsiter(data: RegisterRequest) = api.register(data)
}