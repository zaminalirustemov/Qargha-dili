package az.lahza.qarghadili.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import az.lahza.qarghadili.R
import az.lahza.qarghadili.ui.theme.AccentOrange
import az.lahza.qarghadili.ui.theme.BorderGray
import az.lahza.qarghadili.ui.theme.DarkAccentOrange
import az.lahza.qarghadili.ui.theme.DarkBorderGray
import az.lahza.qarghadili.ui.theme.Dimens
import az.lahza.qarghadili.ui.theme.TextColor

@Composable
fun CrownLanguageButton(
    modifier: Modifier,
    icon: Int? = null,
    text: String = stringResource(R.string.convert_button_label),
    normalColor: Color = AccentOrange,
    pressedColor: Color = DarkAccentOrange,
    textColor: Color = TextColor,
    fontSize: TextUnit = Dimens._18SP,
    cornerRadius: Dp = Dimens.MediumLarge,
    offset: Dp = Dimens.Medium,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()


    val animatedNormalColor by animateColorAsState(
        targetValue = if (enabled) normalColor else BorderGray,
        animationSpec = tween(
            durationMillis = 500,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        )
    )
    val animatedPressedColor by animateColorAsState(
        targetValue = if (enabled) pressedColor else DarkBorderGray,
        animationSpec = tween(
            durationMillis = 500,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        )
    )

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
                            color = animatedPressedColor,
                            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                            size = Size(
                                size.width,
                                size.height
                            )
                        )
                        if (!isPressed) {
                            drawRoundRect(
                                color = animatedNormalColor,
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

            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = TextColor,
                    modifier = Modifier
                        .size(Dimens._52DP)
                        .padding(Dimens._14DP)
                        .align(Alignment.Center)
                        .offset(y = if (isPressed) Dimens.Zero else (-offset / 2))
                )
            } else {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier
                        .padding(top = Dimens._16DP, bottom = Dimens._16DP)
                        .align(Alignment.Center)
                        .offset(
                            x = Dimens.Zero,
                            y = if (isPressed) Dimens.Zero else (-offset / 2)
                        )
                )
            }

        }
    }
}
