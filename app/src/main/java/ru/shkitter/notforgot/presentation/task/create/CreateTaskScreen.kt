package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledTextField
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.theme.NotForgotTheme

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultCreateTaskScreen() {
    NotForgotTheme {
        Surface {
            CreateTaskContent(
                title = "",
                onTitleChanged = {},
                description = "",
                onDescriptionChanged = {},
                categories = listOf(),
                selectedCategory = null,
                onCategorySelect = {}
            )
        }
    }
}

@Composable
fun CreateTaskScreen(task: Task?, onBackClick: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val viewModel = getViewModel<CreateTaskViewModel> {
        parametersOf(task)
    }

    val title by viewModel.title.observeAsState(task?.title.orEmpty())
    val description by viewModel.description.observeAsState(task?.description.orEmpty())
    val categories by viewModel.categories.observeAsState(listOf())
    val selectedCategory by viewModel.selectedCategory.observeAsState()

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = if (task != null) R.string.create_task_edit_title else R.string.create_task_title),
                onBackClick = onBackClick
            )
        }) {
        Surface {
            CreateTaskContent(
                title = title,
                onTitleChanged = viewModel::onTitleChanged,
                description = description,
                onDescriptionChanged = viewModel::onDescriptionChanged,
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelect = viewModel::onCategorySelected
            )
        }
    }
}

@Composable
private fun CreateTaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    categories: List<Category>,
    selectedCategory: Category?,
    onCategorySelect: (Category) -> Unit,
) {
    var categoryExpanded by remember { mutableStateOf(false) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.create_task_task_title),
            color = Color.Black,
            style = MaterialTheme.typography.h4
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppFilledTextField(
            value = title,
            onValueChange = onTitleChanged,
            placeholder = stringResource(id = R.string.create_task_task_title),
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppFilledTextField(
            value = description,
            onValueChange = onDescriptionChanged,
            label = stringResource(id = R.string.create_task_task_description),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                AppFilledTextField(
                    value = selectedCategory?.name.orEmpty(),
                    onValueChange = {},
                    enabled = false,
                    placeholder = stringResource(id = R.string.create_task_task_category),
                    modifier = Modifier
                        .clickable {
                            categoryExpanded = true
                        }
                        .onSizeChanged { size ->
                            dropDownWidth = size.width
                        }
                )

                DropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { dropDownWidth.toDp() })
                        .height(250.dp)
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(onClick = {
                            onCategorySelect.invoke(category)
                            categoryExpanded = false
                        }) {
                            Text(
                                text = category.name,
                                style = if (selectedCategory == category) {
                                    MaterialTheme.typography.h4
                                } else {
                                    MaterialTheme.typography.h5
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
