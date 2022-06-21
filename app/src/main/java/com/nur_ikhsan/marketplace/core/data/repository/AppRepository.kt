package com.nur_ikhsan.marketplace.core.data.repository


import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.nur_ikhsan.marketplace.Util.Prefs
import com.nur_ikhsan.marketplace.core.data.source.local.LocalDataSource
import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko
import com.nur_ikhsan.marketplace.core.data.source.remote.RemoteDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.network.Resource
import com.nur_ikhsan.marketplace.core.data.source.remote.request.CreatTokoRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

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

        //---------------------update/edit profile----------------------//
    fun updateProfile(data:UpdateProfileRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.update(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.succes(user))
                    emit(Resource.succes(body?.data))
                    logs("succes:"+body.toString())
                } else{

                    emit(Resource.error(it.getErrorBody(ErrorCustom::class.java)?.message
                        ?:"Terjadi kesalahan", null))
                }
            }
        }   catch (e:Exception){
            emit(Resource.error(e.message?: "Koneksi buruk", null))
        }
    }


    //---------------------upload Image----------------------//
    fun uploadUser(id:Int? = null, fileImage: MultipartBody.Part?= null) = flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.succes(user))
                    emit(Resource.succes(body?.data))
                    logs("succes:"+body.toString())
                } else{

                    emit(Resource.error(it.getErrorBody(ErrorCustom::class.java)?.message
                        ?:"Terjadi kesalahan", null))
                }
            }
        }   catch (e:Exception){
            emit(Resource.error(e.message?: "Koneksi buruk", null))
        }
    }


    //---------------------buat toko----------------------//
    fun createToko(data: CreatTokoRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.creatToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.succes(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: " error", null))
                }
            }
        } catch (e: java.lang.Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    //------------------toko saya--------------------//
    fun getUser(id: Int? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.getUser(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.succes(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: java.lang.Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }


    //-------------------get alamat toko----------------------------//
    fun getAlamatToko() = flow {
        emit(Resource.loading(null))
        try {
            remote.getAlamatToko().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.succes(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: java.lang.Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }


    //--------------------------creat Alamat toko----------------------------//
    fun createAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.creatAlamatToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.succes(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: java.lang.Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }


    class ErrorCustom(
        val ok :Boolean,
        val error_code :Int,
        val description : String? = null,
        val message : String? = null
    )




}