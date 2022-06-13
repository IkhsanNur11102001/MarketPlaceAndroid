package com.nur_ikhsan.marketplace.core.di


import com.nur_ikhsan.marketplace.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }

}