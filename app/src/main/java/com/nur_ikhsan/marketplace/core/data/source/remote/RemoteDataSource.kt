package com.nur_ikhsan.marketplace.core.data.source.remote

import com.nur_ikhsan.marketplace.core.data.source.remote.network.ApiService
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data:LoginRequest) = api.login(data)
    suspend fun regsiter(data: RegisterRequest) = api.register(data)
    suspend fun update(data: UpdateProfileRequest) = api.update(data.id, data)
}