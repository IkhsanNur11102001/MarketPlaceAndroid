package com.nur_ikhsan.marketplace.core.data.repository


import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.local.LocalDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.RemoteDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.network.Resource
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import kotlinx.coroutines.flow.flow

class AppRepository (val local : LocalDataSource, val remote: RemoteDataSource){


        //--------------------login---------------------------//
    fun login(data:LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.succes(user))
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


    //-------------------------register-----------------------------//
    fun register(data:RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.regsiter(data).let {
                if (it.isSuccessful){
                    //Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    //Prefs.setUser(user)
                    emit(Resource.succes(user))
                    emit(Resource.succes(body?.data))
                    logs("succes:"+body.toString())
                } else{

                    emit(Resource.error(it.getErrorBody(ErrorCustom::class.java)?.message
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
        val description : String? = null,
        val message : String? = null
    )




}