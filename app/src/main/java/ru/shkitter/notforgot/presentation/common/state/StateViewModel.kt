package ru.shkitter.notforgot.presentation.common.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shkitter.notforgot.presentation.common.ViewEvent
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData

abstract class StateViewModel<A> : ViewModel() {
    protected val _contentState = MutableLiveData<ContentState>()
    val contentState = _contentState.asLiveData()

    protected val _event = MutableLiveData<ViewEvent<A>>()
    val event = _event.asLiveData()
}