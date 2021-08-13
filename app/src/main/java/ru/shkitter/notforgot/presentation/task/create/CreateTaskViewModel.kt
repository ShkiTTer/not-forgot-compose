package ru.shkitter.notforgot.presentation.task.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.shkitter.domain.task.GetCreateTaskDataUseCase
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.state.StateViewModel
import java.time.Instant

class CreateTaskViewModel(
    private val task: Task?,
    private val getCreateTaskDataUseCase: GetCreateTaskDataUseCase
) : StateViewModel<Unit>() {

    private val _title = MutableLiveData<String>()
    val title = _title.asLiveData()

    private val _description = MutableLiveData<String>()
    val description = _description.asLiveData()

    private val _categories = MutableLiveData<List<Category>>()
    val categories = _categories.asLiveData()

    private val _priorities = MutableLiveData<List<Priority>>()
    val priorities = _priorities.asLiveData()

    private val _selectedCategory = MutableLiveData<Category?>()
    val selectedCategory = _selectedCategory.asLiveData()

    private val _selectedPriority = MutableLiveData<Priority?>()
    val selectedPriority = _selectedPriority.asLiveData()

    private val _selectedDeadline = MutableLiveData<Instant?>()
    val selectedDeadline = _selectedDeadline.asLiveData()

    init {
        fetchCreateTaskData()
    }

    fun fetchCreateTaskData() {
        viewModelScope.launch {
            getCreateTaskDataUseCase()
                .collect { result ->
                    result
                        .onSuccess { data ->
                            _categories.value = data.categories
                            _priorities.value = data.priorities
                        }
                        .onFailure { }
                }
        }
    }

    fun onTitleChanged(value: String) {
        _title.value = value
    }

    fun onDescriptionChanged(value: String) {
        _description.value = value
    }

    fun onCategorySelected(value: Category) {
        _selectedCategory.value = value
    }

    fun onDeadlineSelected(value: Instant) {
        _selectedDeadline.value = value
    }

    fun onPrioritySelected(value: Priority) {
        _selectedPriority.value = value
    }
}