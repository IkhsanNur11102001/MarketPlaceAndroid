package com.nur_ikhsan.marketplace.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nur_ikhsan.marketplace.core.data.repository.AppRepository

class LoginViewModel(val repo: AppRepository) :ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "home"
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("ini saya")
    }
}