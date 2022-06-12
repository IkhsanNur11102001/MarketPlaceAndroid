package com.nur_ikhsan.marketplace.core.data.source.remote.network

data class Resource<out T>(val state: State, val data: T?, val message: String?){

    companion object{

        fun <T> succes(data: T?): Resource<T>{
            return Resource(State.SUCCES, data, null)
        }
        fun <T> error(msg: String, data: T?): Resource<T>{
            return Resource(State.ERROR, data, msg)
        }
        fun <T> loading(data: T?): Resource<T>{
            return Resource(State.LOADING, data, null)
        }
    }
}
