package com.example.jetgames.filter.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.R
import com.example.jetgames.filter.components.FilterToolbar
import com.example.jetgames.filter.components.OrderItem
import com.example.jetgames.filter.viewmodel.OrdersScreenViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    currentOrder: String,
    navigateUp: () -> Unit,
    onApplyButtonClick: (String) -> Unit,
) {

    val viewModel = viewModel<OrdersScreenViewModel>()

    val options = viewModel.orders.collectAsState()

    val selectedOrder = rememberSaveable {
        mutableStateOf(currentOrder)
    }

    DefaultScreenUI(toolbar = { FilterToolbar(
        title = "Order By", navigateUp = navigateUp,
        enableApplyButton = currentOrder != selectedOrder.value,
        onApplyButtonClick = {
            onApplyButtonClick(selectedOrder.value)
        },
    ) }) {

        LazyColumn(modifier = modifier) {
            items(options.value) {
                OrderItem(order = it, selected = it == selectedOrder.value) { order ->
                    selectedOrder.value = order
                }
            }
        }
    }
}
