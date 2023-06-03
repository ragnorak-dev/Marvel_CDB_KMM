package com.ragnorak.marvelcdb.android.ui

import com.ragnorak.marvelcdb.ResultData

sealed class ViewState <out T> {

    object Idle: ViewState<Nothing>()
    object Loading: ViewState<Nothing>()
    data class Success<out R>(val data: R): ViewState<R>()
    data class Error(val errorMessage: String): ViewState<Nothing>()
}
