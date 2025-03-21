package az.lahza.qarghadili.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.lahza.qarghadili.R
import az.lahza.qarghadili.ui.theme.AccentOrange
import az.lahza.qarghadili.ui.theme.TextColor


@Composable
fun GenerateCrownLanguageButton(
    modifier: Modifier,
    text: String = stringResource(R.string.chevir),
    normalColor: Color = AccentOrange,
    pressedColor: Color = Color(0xFFC56C16),
    textColor: Color = TextColor,
    fontSize: TextUnit = 22.sp,
    cornerRadius: Dp = 8.dp,
    offset: Dp = 6.dp,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null
                )

                .drawWithCache {
                    onDrawBehind {
                        drawRoundRect(
                            color = pressedColor,
                            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                            size = Size(
                                size.width,
                                size.height
                            )
                        )
                        if (!isPressed) {
                            drawRoundRect(
                                color = normalColor,
                                cornerRadius = CornerRadius(
                                    cornerRadius.toPx(),
                                    cornerRadius.toPx()
                                ),
                                size = Size(
                                    size.width,
                                    size.height - offset.toPx()
                                )
                            )
                        }

                    }
                }

        ) {
            Text(
                text = text,
                fontSize = fontSize,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .align(Alignment.Center)
                    .offset(
                        x = 0.dp,
                        y = if (isPressed) 0.dp else (-offset / 2)
                    )
            )
        }
    }
}