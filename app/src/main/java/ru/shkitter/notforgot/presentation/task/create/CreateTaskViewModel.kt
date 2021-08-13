package ru.shkitter.notforgot.presentation.task.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.shkitter.domain.task.CreateTaskUseCase
import ru.shkitter.domain.task.GetCreateTaskDataUseCase
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.CreateTaskParams
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.state.ContentState
import ru.shkitter.notforgot.presentation.common.state.StateViewModel
import java.time.Instant

class CreateTaskViewModel(
    private val task: Task?,
    private val getCreateTaskDataUseCase: GetCreateTaskDataUseCase,
    private val createTaskUseCase: CreateTaskUseCase
) : StateViewModel<Unit>() {

    private val _title = MutableLiveData(task?.title.orEmpty())
    val title = _title.asLiveData()

    private val _description = MutableLiveData(task?.description.orEmpty())
    val description = _description.asLiveData()

    private val _categories = MutableLiveData<List<Category>>()
    val categories = _categories.asLiveData()

    private val _priorities = MutableLiveData<List<Priority>>()
    val priorities = _priorities.asLiveData()

    private val _selectedCategory = MutableLiveData(task?.category)
    val selectedCategory = _selectedCategory.asLiveData()

    private val _selectedPriority = MutableLiveData(task?.priority)
    val selectedPriority = _selectedPriority.asLiveData()

    private val _selectedDeadline = MutableLiveData(task?.deadline)
    val selectedDeadline = _selectedDeadline.asLiveData()

    init {
        fetchCreateTaskData()
    }

    fun fetchCreateTaskData() {
        viewModelScope.launch {
            getCreateTaskDataUseCase()
                .onStart { _contentState.value = ContentState.Loading }
                .collect { result ->
                    result
                        .onSuccess { data ->
                            _contentState.value = ContentState.Content
                            _categories.value = data.categories
                            _priorities.value = data.priorities
                        }
                        .onFailure { }
                }
        }
    }

    fun onSaveTask() {

    }

    private fun createTask() {
        viewModelScope.launch {
            val localTitle = title.value.orEmpty()
            val localDescription = description.value.orEmpty()
            val localDeadline = selectedDeadline.value ?: return@launch
            val category = selectedCategory.value ?: return@launch
            val priority = selectedPriority.value ?: return@launch

            val params = CreateTaskParams(
                title = localTitle,
                description = localDescription,
                done = false,
                deadline = localDeadline,
                categoryId = category.id,
                priorityId = priority.id
            )

            createTaskUseCase(params)
                .collect { result ->
                    result
                        .onSuccess {  }
                        .onFailure {  }
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