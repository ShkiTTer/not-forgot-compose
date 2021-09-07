package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledButton
import ru.shkitter.notforgot.presentation.common.components.AppFilledTextField
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.components.ContentStateBox
import ru.shkitter.notforgot.presentation.common.theme.BgBlackColor
import ru.shkitter.notforgot.presentation.common.theme.NotForgotTheme
import ru.shkitter.notforgot.presentation.common.theme.TextBlackColor
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultCreateTaskScreen() {
    NotForgotTheme {
        CreateTaskScreen(task = null, navController = rememberNavController())
    }
}

@Composable
fun CreateTaskScreen(task: Task?, navController: NavController) {
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
                onBackClick = { navController.popBackStack() }
            )
        }) {
        ContentStateBox(
            viewModel = viewModel,
            content = {
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
                    onPrioritySelected = viewModel::onPrioritySelected,
                    onSaveClick = viewModel::onSaveTask
                )
            },
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier.navigationBarsWithImePadding()
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
    onPrioritySelected: (Priority) -> Unit,
    onSaveClick: () -> Unit
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
            singleLine = false,
            maxLength = 50,
            showCounter = true
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
        Spacer(modifier = Modifier.height(16.dp))

        AppFilledButton(
            onClick = { onSaveClick.invoke() },
            text = stringResource(id = R.string.common_save),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
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
