package com.nur_ikhsan.marketplace.ui.alamat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.nur_ikhsan.marketplace.core.data.repository.AppRepository



class AlamatTokoViewModel(private val repo: AppRepository) :ViewModel() {

fun get() = repo.getAlamatToko().asLiveData()


}