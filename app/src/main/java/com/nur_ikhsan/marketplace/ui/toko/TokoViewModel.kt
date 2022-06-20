package com.nur_ikhsan.marketplace.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.nur_ikhsan.marketplace.core.data.repository.AppRepository
import com.nur_ikhsan.marketplace.core.data.source.remote.request.CreatTokoRequest


class TokoViewModel(val repo: AppRepository) :ViewModel() {

fun creatToko(data: CreatTokoRequest) = repo.createToko(data).asLiveData()


}