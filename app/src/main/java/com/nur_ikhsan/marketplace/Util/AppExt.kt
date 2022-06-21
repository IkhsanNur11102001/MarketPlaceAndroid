package com.nur_ikhsan.marketplace.Util

fun String?.defaultError(): String {
    return this ?: Constant.DEFALUT_ERROR
}