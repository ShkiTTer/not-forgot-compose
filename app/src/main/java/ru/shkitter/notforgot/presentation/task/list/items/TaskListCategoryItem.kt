package ru.shkitter.notforgot.presentation.task.list.items

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.shkitter.domain.task.model.Category
import ru.shkitter.notforgot.presentation.common.theme.TextBlackColor

@Preview(showBackground = true)
@Composable
fun DefaultTaskListCategoryItem() {
    TaskListCategoryItem(category = Category(0, "Example"))
}

@Composable
fun TaskListCategoryItem(category: Category, modifier: Modifier = Modifier) {
    Text(
        text = category.name,
        style = MaterialTheme.typography.h2,
        color = TextBlackColor,
        modifier = modifier
    )
}