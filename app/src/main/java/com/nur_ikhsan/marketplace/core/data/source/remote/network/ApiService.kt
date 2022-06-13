package com.nur_ikhsan.marketplace.core.data.source.remote.network

import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest,
    ): Response<LoginResponse>


    //"https://127.0.0.1:8000/api/register"
    @POST("register")
    suspend fun register(
        @Body user:RegisterRequest
    ): Response<LoginResponse>

    @PUT("update-user/{id}")
    suspend fun update(
        @Path("id") int: Int,
        @Body user:UpdateProfileRequest
    ): Response<LoginResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser(
        @Path("id") int: Int? = null,
        @Part user:MultipartBody.Part? = null
    ): Response<LoginResponse>



}