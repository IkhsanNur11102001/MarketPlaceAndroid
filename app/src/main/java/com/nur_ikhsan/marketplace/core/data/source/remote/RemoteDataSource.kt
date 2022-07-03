package com.nur_ikhsan.marketplace.core.data.source.remote

import com.nur_ikhsan.marketplace.Util.getTokoId
import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko
import com.nur_ikhsan.marketplace.core.data.source.remote.network.ApiService
import com.nur_ikhsan.marketplace.core.data.source.remote.request.CreatTokoRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data:LoginRequest) = api.login(data)

    suspend fun regsiter(data: RegisterRequest) = api.register(data)

    suspend fun update(data: UpdateProfileRequest) = api.update(data.id, data)

    suspend fun creatToko(data:CreatTokoRequest) = api.creatToko(data)

    suspend fun uploadUser(id:Int? = null, fileImage:MultipartBody.Part?= null) = api.uploadUser(id, fileImage)

    suspend fun getUser(id: Int? = null) = api.getUser(id)

    suspend fun getAlamatToko() = api.getAlamatToko(getTokoId())

    suspend fun creatAlamatToko(data: AlamatToko) = api.creatAlamatToko(data)

    suspend fun updateAlamatToko(data: AlamatToko) = api.updateAlamat(data.id, data)


}