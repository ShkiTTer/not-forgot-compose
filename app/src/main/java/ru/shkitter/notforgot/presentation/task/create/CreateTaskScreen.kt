package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledTextField
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.theme.*
import ru.shkitter.notforgot.presentation.common.utils.DateFormattingUtils
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultCreateTaskScreen() {
    NotForgotTheme {
        CreateTaskContent(
            title = "",
            onTitleChanged = {},
            description = "",
            onDescriptionChanged = {},
            categories = listOf(),
            selectedCategory = null,
            onCategorySelect = {},
            deadline = null,
            onDeadLineSelectClick = {},
            priorities = listOf(),
            selectedPriority = null,
            onPrioritySelected = {}
        )
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
    val priorities by viewModel.priorities.observeAsState(listOf())
    val selectedCategory by viewModel.selectedCategory.observeAsState()
    val selectedDeadline by viewModel.selectedDeadline.observeAsState()
    val selectedPriority by viewModel.selectedPriority.observeAsState()

    val deadLinePickerDialog = buildDatePickerDialog(
        date = selectedDeadline,
        onDateSelected = viewModel::onDeadlineSelected
    )

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = if (task != null) R.string.create_task_edit_title else R.string.create_task_title),
                onBackClick = onBackClick
            )
        }) {
        CreateTaskContent(
            title = title,
            onTitleChanged = viewModel::onTitleChanged,
            description = description,
            onDescriptionChanged = viewModel::onDescriptionChanged,
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelect = viewModel::onCategorySelected,
            deadline = selectedDeadline,
            onDeadLineSelectClick = {
                deadLinePickerDialog.show()
            },
            priorities = priorities,
            selectedPriority = selectedPriority,
            onPrioritySelected = viewModel::onPrioritySelected
        )
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
    deadline: Instant?,
    onDeadLineSelectClick: () -> Unit,
    priorities: List<Priority>,
    selectedPriority: Priority?,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        AppFilledTextField(
            value = title,
            onValueChange = onTitleChanged,
            label = stringResource(id = R.string.create_task_task_title),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        AppFilledTextField(
            value = description,
            onValueChange = onDescriptionChanged,
            label = stringResource(id = R.string.create_task_task_description),
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp),
            singleLine = false
        )
        Spacer(modifier = Modifier.height(24.dp))

        DeadlineField(deadline = deadline, onDeadLineSelectClick = onDeadLineSelectClick)
        Spacer(modifier = Modifier.height(24.dp))

        CategorySelect(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelect = onCategorySelect
        )
        Spacer(modifier = Modifier.height(24.dp))

        PrioritySelect(
            priorities = priorities,
            selectedPriority = selectedPriority,
            onPrioritySelected = onPrioritySelected
        )
    }
}

@Composable
private fun CategorySelect(
    categories: List<Category>,
    selectedCategory: Category?,
    onCategorySelect: (Category) -> Unit,
) {
    var categoryExpanded by remember { mutableStateOf(false) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            AppFilledTextField(
                value = selectedCategory?.name.orEmpty(),
                onValueChange = {},
                enabled = false,
                placeholder = stringResource(id = R.string.create_task_task_category),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = TextBlackColor,
                    disabledPlaceholderColor = TextGrayColor,
                    disabledIndicatorColor = BgGrayColor,
                    backgroundColor = BgTextField
                ),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth()
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

        Icon(
            imageVector = Icons.Outlined.AddBox,
            contentDescription = null,
            tint = Color.Black.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun DeadlineField(deadline: Instant?, onDeadLineSelectClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            AppFilledTextField(
                value = deadline?.let { DateFormattingUtils.getFullDigitDate(it) }.orEmpty(),
                onValueChange = {},
                placeholder = stringResource(id = R.string.create_task_task_deadline),
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = TextBlackColor,
                    disabledPlaceholderColor = TextGrayColor,
                    disabledIndicatorColor = BgGrayColor,
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = null,
            tint = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.clickable { onDeadLineSelectClick.invoke() }
        )
    }
}

@Composable
private fun PrioritySelect(
    priorities: List<Priority>,
    selectedPriority: Priority?,
    onPrioritySelected: (Priority) -> Unit
) {
    val priorityExpanded = remember { mutableStateOf(false) }
    val dropDownWidth = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        AppFilledTextField(
            value = selectedPriority?.name.orEmpty(),
            onValueChange = {},
            placeholder = stringResource(id = R.string.create_task_task_priority),
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = TextBlackColor,
                disabledPlaceholderColor = TextGrayColor,
                disabledIndicatorColor = BgGrayColor,
                backgroundColor = BgTextField
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size -> dropDownWidth.value = size.width }
                .clickable { priorityExpanded.value = true }
        )

        DropdownMenu(
            expanded = priorityExpanded.value,
            onDismissRequest = { priorityExpanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownWidth.value.toDp() })
                .height(250.dp)
        ) {

            priorities.forEach { priority ->

                DropdownMenuItem(
                    onClick = {
                        priorityExpanded.value = false
                        onPrioritySelected.invoke(priority)
                    }) {

                    Text(
                        text = priority.name,
                        style = if (priority == selectedPriority) {
                            MaterialTheme.typography.h4
                        } else {
                            MaterialTheme.typography.h5
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun buildDatePickerDialog(
    date: Instant?,
    onDateSelected: (Instant) -> Unit
): MaterialDialog {
    val dialog = remember { MaterialDialog() }

    var selectedDate by remember {
        mutableStateOf(
            date?.atZone(ZoneId.systemDefault())?.toLocalDate() ?: LocalDate.now()
        )
    }

    dialog.build(
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.common_ok),
                onClick = {
                    val instant = selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
                    onDateSelected.invoke(instant)
                })
            negativeButton(text = stringResource(id = R.string.common_cancel))
        }) {
        datepicker(
            initialDate = selectedDate,
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = BgBlackColor,
                headerTextColor = Color.White,
                activeBackgroundColor = BgBlackColor,
                activeTextColor = Color.White,
                inactiveTextColor = TextBlackColor
            )
        ) { pickedDate ->
            selectedDate = pickedDate
        }
    }

    return dialog
}
