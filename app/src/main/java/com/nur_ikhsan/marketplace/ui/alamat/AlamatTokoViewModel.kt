package com.nur_ikhsan.marketplace.ui.alamat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.nur_ikhsan.marketplace.core.data.repository.AppRepository
import com.nur_ikhsan.marketplace.core.data.source.model.AlamatToko


class AlamatTokoViewModel(private val repo: AppRepository) :ViewModel() {

fun get() = repo.getAlamatToko().asLiveData()
fun create(data: AlamatToko) = repo.createAlamatToko(data).asLiveData()
    fun update(data: AlamatToko) = repo.updateAlamatToko(data).asLiveData()

}