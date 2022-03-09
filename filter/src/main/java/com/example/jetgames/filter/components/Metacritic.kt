package com.example.jetgames.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XLightGray
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference

@Composable
fun Metacritic(
    modifier: Modifier = Modifier,
    selectedMetacritic:MetacriticPreference?=null
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(50.dp)
        .clickable { /* TODO: show metacritic dialog */   }
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_8)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Metacritic",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onPrimary)

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.dimen_8))) {
                    selectedMetacritic?.let {
                        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_4))) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                    Text(text = "Min",
                                        fontSize = 8.sp,
                                        color = MaterialTheme.colors.onPrimary)
                                }
                                Text(text = it.min.toString(),
                                    style = MaterialTheme.typography.subtitle2,
                                    color = MaterialTheme.colors.onPrimary)
                            }
                            Divider(color = XLightGray,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .width(dimensionResource(id = R.dimen.dimen_8)))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                    Text(text = "Max",
                                        fontSize = 8.sp,
                                        color = MaterialTheme.colors.onPrimary)
                                }
                                Text(text = it.max.toString(),
                                    style = MaterialTheme.typography.subtitle2,
                                    color = MaterialTheme.colors.onPrimary)
                            }
                        }
                    }
                    IconButton(
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_next),
                                contentDescription = null,
                                tint = Color.White)
                        },
                        onClick = { /* TODO: show metacritic dialog */  },
                    )
                }
            }
        }

@Preview
@Composable
fun MetacriticPrev() {
    JetgamesTheme {
        Metacritic()
    }
}