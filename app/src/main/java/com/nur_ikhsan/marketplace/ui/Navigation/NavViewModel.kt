package com.nur_ikhsan.marketplace.ui.Navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.nur_ikhsan.marketplace.core.data.repository.AppRepository
import com.nur_ikhsan.marketplace.core.data.source.remote.request.CreatTokoRequest


class NavViewModel(val repo: AppRepository) :ViewModel() {

fun getUser(id:Int) = repo.getUser(id).asLiveData()


}