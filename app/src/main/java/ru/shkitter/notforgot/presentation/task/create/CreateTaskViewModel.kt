package ru.shkitter.notforgot.presentation.task.create

import androidx.lifecycle.MutableLiveData
import ru.shkitter.domain.task.model.Category
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.state.StateViewModel

class CreateTaskViewModel : StateViewModel<Unit>() {
    private val _title = MutableLiveData<String>()
    val title = _title.asLiveData()

    private val _description = MutableLiveData<String>()
    val description = _description.asLiveData()

    private val _categories = MutableLiveData<List<Category>>()
    val categories = _categories.asLiveData()

    fun onTitleChanged(value: String) {
        _title.value = value
    }

    fun onDescriptionChanged(value: String) {
        _description.value = value
    }
}