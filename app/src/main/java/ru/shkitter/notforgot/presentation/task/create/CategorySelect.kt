package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.shkitter.domain.task.model.Category
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledTextField
import ru.shkitter.notforgot.presentation.common.theme.*

@Composable
internal fun CategorySelect(
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
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dropdown),
                        contentDescription = null,
                        tint = BlackAlpha60
                    )
                },
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
                    .heightIn(max = 250.dp)
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