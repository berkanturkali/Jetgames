package com.example.jetgames.filter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.XXLightGray
import com.example.jetgames.common.util.calculateRgbFromMetacritic
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference
import com.example.jetgames.filter.viewmodel.FilterScreenViewModel

@Composable
fun Metacritic(
    modifier: Modifier = Modifier,
    selectedMetacritic: MetacriticPreference? = null,
    viewModel: FilterScreenViewModel,
) {

    val min = viewModel.min.collectAsState()

    val max = viewModel.max.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_8)),
    ) {
        Text(text = "Metacritic",
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8),
                top = dimensionResource(
                    id = R.dimen.dimen_8)),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary)

        Divider(thickness = 0.5.dp)

        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16))) {

            SliderItem(
                title = "Min (${min.value.toInt()} - ${max.value.toInt()})",
                value = min.value,
                onValueChange = viewModel::setMin)


            SliderItem(
                title = "Max (${max.value.toInt()} - 100)",
                value = max.value,
                onValueChange =viewModel::setMax,
               onValueChangeFinished = viewModel::onValueChangeFinishedForMax)
        }
    }
}

@Composable
private fun SliderItem(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit = {},
) {

    Text(text = title,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.onPrimary)
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..100f,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.onPrimary,
            activeTickColor = MaterialTheme.colors.onPrimary,
            activeTrackColor = value.toInt().calculateRgbFromMetacritic(),
            inactiveTrackColor = XXLightGray.copy(alpha = 0.2f)
        ),
        onValueChangeFinished = onValueChangeFinished
    )

}