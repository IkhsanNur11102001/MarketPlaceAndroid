package com.nur_ikhsan.marketplace.core.di


import com.nur_ikhsan.marketplace.ui.Navigation.NavViewModel
import com.nur_ikhsan.marketplace.ui.auth.AuthViewModel
import com.nur_ikhsan.marketplace.ui.toko.TokoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { TokoViewModel(get()) }
    viewModel { NavViewModel(get()) }
}