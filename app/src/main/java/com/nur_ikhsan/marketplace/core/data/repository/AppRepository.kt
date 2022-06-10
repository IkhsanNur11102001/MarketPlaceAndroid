package com.nur_ikhsan.marketplace.core.data.repository

import com.nur_ikhsan.marketplace.core.data.source.local.LocalDataSource
import com.nur_ikhsan.marketplace.core.data.source.remote.RemoteDataSource

class AppRepository (val local : LocalDataSource, val remote: RemoteDataSource){

}