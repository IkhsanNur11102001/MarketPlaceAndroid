package com.nur_ikhsan.marketplace.core.data.source.remote.network

import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko
import com.nur_ikhsan.marketplace.core.data.source.remote.request.CreatTokoRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.response.BaseListResponse
import com.nur_ikhsan.marketplace.core.data.source.remote.response.BaseSingelResponse
import com.nur_ikhsan.marketplace.core.data.source.remote.response.LoginResponse
import com.nur_ikhsan.marketplace.core.data.source.remote.response.TokoResponse
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

    @POST("toko")
    suspend fun creatToko(
        @Body data:CreatTokoRequest
    ): Response<BaseSingelResponse<TokoResponse>>

    @GET("toko-user/{id}")
    suspend fun getUser(
        @Path("id") int: Int? = null,
    ): Response<LoginResponse>

    @GET("alamat-toko/{id}")
    suspend fun getAlamatToko(
        @Path("id") idToko: Int? = null,
    ): Response<BaseListResponse<AlamatToko>>

    @POST("alamat-toko")
    suspend fun creatAlamatToko(
        @Body data:AlamatToko
    ): Response<BaseSingelResponse<AlamatToko>>

}