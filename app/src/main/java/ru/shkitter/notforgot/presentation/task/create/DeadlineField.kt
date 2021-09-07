package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledTextField
import ru.shkitter.notforgot.presentation.common.theme.BgGrayColor
import ru.shkitter.notforgot.presentation.common.theme.TextBlackColor
import ru.shkitter.notforgot.presentation.common.theme.TextGrayColor
import ru.shkitter.notforgot.presentation.common.utils.DateFormattingUtils
import java.time.Instant

@Composable
internal fun DeadlineField(deadline: Instant?, onDeadLineSelectClick: () -> Unit) {
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