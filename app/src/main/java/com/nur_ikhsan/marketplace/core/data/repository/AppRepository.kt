package com.nur_ikhsan.marketplace.core.data.repository

import android.util.Log
import com.inyongtisto.myhelper.extension.logs
import com.nur_ikhsan.marketplace.core.data.source.local.LocalDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.RemoteDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository (val local : LocalDataSource, val remote: RemoteDataSource){

    fun login(data:LoginRequest) = flow {
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    logs("succes:"+body.toString())
                    emit(body)
                } else{
                   logs("Error:"+"Keterangan error")

                }
            }
        }   catch (e:Exception){
            logs("login: Error yang di handle:"+ e.message)
        }
    }

}