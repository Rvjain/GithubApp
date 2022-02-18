package com.rv.githubapp.ui

sealed class BaseViewState<out T> {
    object LOADING: BaseViewState<Nothing>()
    class SUCCESS<T>(val data: List<T>): BaseViewState<T>()
    class ERROR(val message: String): BaseViewState<Nothing>()
}