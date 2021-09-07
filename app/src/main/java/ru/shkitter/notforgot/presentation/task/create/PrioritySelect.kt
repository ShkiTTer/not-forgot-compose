package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledTextField
import ru.shkitter.notforgot.presentation.common.theme.*


@Preview(showBackground = true)
@Composable
private fun DefaultPrioritySelect() {
    PrioritySelect(priorities = listOf(), selectedPriority = null, onPrioritySelected = {})
}

@Composable
internal fun PrioritySelect(
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
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dropdown),
                    contentDescription = null,
                    tint = BlackAlpha60
                )
            },
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
                .heightIn(max = 250.dp)
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