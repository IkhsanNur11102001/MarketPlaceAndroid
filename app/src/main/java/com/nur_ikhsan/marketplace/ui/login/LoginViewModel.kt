package com.nur_ikhsan.marketplace.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nur_ikhsan.marketplace.core.data.repository.AppRepository
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest

class LoginViewModel(val repo: AppRepository) :ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "hallo sayang"
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("ini saya")
    }

    fun login(data:LoginRequest) = repo.login(data).asLiveData()
}