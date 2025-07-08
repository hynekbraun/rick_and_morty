package com.hynekbraun.rickandmorty.components.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.R
import com.hynekbraun.rickandmorty.components.extensions.clickableWithoutRipple
import com.hynekbraun.rickandmorty.components.theme.RMTheme

@Composable
public fun ActionBarSearchComponent(
    text: String,
    prompt: String,
    onTextChanged: (String) -> Unit,
    onDeleteClicked: () -> Unit,
    onLeadingIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val focusRequester = remember { FocusRequester() }
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickableWithoutRipple {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onLeadingIconClick.invoke()
                    },
                painter = painterResource(R.drawable.chevron_back),
                contentDescription = null,
            )

            BasicTextField(
                modifier = Modifier.weight(1f).focusRequester(focusRequester),
                value = text,
                singleLine = true,
                cursorBrush = SolidColor(RMTheme.colors.accentPrimary),
                onValueChange = { onTextChanged(it) },
                textStyle = RMTheme.typography.paragraphMedium,
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = prompt,
                            color = RMTheme.colors.foregroundsSecondary,
                            style = RMTheme.typography.paragraphMedium
                        )
                    }
                    innerTextField()
                }
            )

            if (text.isNotBlank()) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickableWithoutRipple {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onDeleteClicked.invoke()
                        },
                    painter = painterResource(R.drawable.close),
                    contentDescription = null,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.25f),
                            Color.Transparent,
                        )
                    )
                )
        )
    }
}