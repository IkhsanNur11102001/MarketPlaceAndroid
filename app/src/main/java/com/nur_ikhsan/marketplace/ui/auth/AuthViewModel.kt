package com.nur_ikhsan.marketplace.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nur_ikhsan.marketplace.core.data.repository.AppRepository
import com.nur_ikhsan.marketplace.core.data.source.remote.request.LoginRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.RegisterRequest
import com.nur_ikhsan.marketplace.core.data.source.remote.request.UpdateProfileRequest

class AuthViewModel(val repo: AppRepository) :ViewModel() {

    fun login(data:LoginRequest) = repo.login(data).asLiveData()
    fun regsiter(data:RegisterRequest) = repo.register(data).asLiveData()
    fun updateProfile(data: UpdateProfileRequest) = repo.updateProfile(data).asLiveData()
}