package ru.shkitter.notforgot.presentation.common.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.shkitter.notforgot.presentation.common.ViewEvent

fun <T> MutableLiveData<ViewEvent<T>>.setEvent(value: T?) {
    value?.let { this.value = ViewEvent(value) }
}

fun <T> MutableLiveData<ViewEvent<T>>.postEvent(value: T?) {
    value?.let { postValue(ViewEvent(value)) }
}

fun MutableLiveData<ViewEvent<Unit>>.postEvent() {
    postValue(ViewEvent(Unit))
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this.map { it }