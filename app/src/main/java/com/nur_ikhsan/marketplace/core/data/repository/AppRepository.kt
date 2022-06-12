package com.nur_ikhsan.marketplace.core.data.repository


import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.nur_ikhsan.marketplace.core.data.source.local.LocalDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.RemoteDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.network.Resource
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository (val local : LocalDataSource, val remote: RemoteDataSource){

    fun login(data:LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.succes(body?.data))
                    logs("succes:"+body.toString())
                } else{

                    emit(Resource.error(it.getErrorBody(ErrorCustom::class.java)?.description
                        ?:"Terjadi kesalahan", null))
                   logs("Error:"+"Keterangan error")
                }
            }
        }   catch (e:Exception){
            emit(Resource.error(e.message?: "Koneksi buruk", null))
            logs("Error: "+e.message)
        }
    }

    class ErrorCustom(
        val ok :Boolean,
        val error_code :Int,
        val description : String? = null
    )




}