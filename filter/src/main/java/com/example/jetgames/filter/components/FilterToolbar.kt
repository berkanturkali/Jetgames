package com.example.jetgames.filter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme

@Composable
fun FilterToolbar(
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary),
        elevation = dimensionResource(id = R.dimen.dimen_24)) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .wrapContentHeight()) {
            val (back, title) = createRefs()
            IconButton(onClick = { /*navigate back */ },
                modifier = Modifier.constrainAs(back) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
//                    width = Dimension.wrapContent
//                    height = Dimension.fillToConstraints
                }) {
                Icon(
                    tint = MaterialTheme.colors.onPrimary,
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(back.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                    .padding(horizontal = dimensionResource(id = R.dimen.dimen_4)),
                textAlign = TextAlign.Center,
                text = "Filter",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Preview
@Composable
fun FilterToolbarPrev() {
    JetgamesTheme {
        FilterToolbar()
    }
}