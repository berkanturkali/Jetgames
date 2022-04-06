package com.example.jetgames.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.preferences.OrderPreference
import java.util.*

@Composable
fun Order(
    modifier: Modifier = Modifier,
    onOrderItemClick: (String) -> Unit,
    orderPref: OrderPreference,
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(
                    id = R.dimen.dimen_8
                )
            )
            .clickable {
                onOrderItemClick("orders_screen")
            }
    ) {
        val (title, selectedOrder, arrow) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = dimensionResource(id = R.dimen.dimen_8)),
            text = "Order By",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

            Text(
                modifier = Modifier.constrainAs(selectedOrder) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(arrow.start)
                },
                text = orderPref.order.name.lowercase().replaceFirstChar {
                    it.titlecase(Locale.getDefault())
                },
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary
            )
        }

        IconButton(
            modifier = Modifier.constrainAs(arrow) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            onClick = { }
        )
    }
}
