package com.nur_ikhsan.marketplace.core.di

import com.nur_ikhsan.marketplace.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}