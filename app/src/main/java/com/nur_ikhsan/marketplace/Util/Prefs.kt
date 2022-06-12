package com.nur_ikhsan.marketplace.Util

import com.chibatching.kotpref.KotprefModel
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel
import com.nur_ikhsan.marketplace.core.data.source.model.User

object Prefs: KotprefModel() {

    var isLogin by booleanPref(false)
    var user by stringPref()

    fun setUser(data: User?){
        user = data.toJson()


    }

    fun getUser(): User? {
        if (user.isEmpty()) return null
        return user.toModel(User::class.java)
    }
}