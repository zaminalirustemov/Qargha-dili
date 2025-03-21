package az.lahza.qarghadili.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
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

/**
 * A button component that displays an icon or text and animates its background color on press.
 *
 * @param modifier Modifier to be applied to the outer container.
 * @param icon An optional resource ID for an icon to be displayed inside the button.
 * @param text The text to display on the button (default is the label for the conversion button).
 * @param normalColor The color of the button when not pressed (default is AccentOrange).
 * @param pressedColor The color of the button when pressed (default is DarkAccentOrange).
 * @param textColor The color of the text or icon (default is TextColor).
 * @param fontSize The font size of the text.
 * @param cornerRadius The radius of the button's corners.
 * @param offset The offset for the icon or text when the button is pressed.
 * @param enabled Determines if the button is enabled (default is true).
 * @param onClick Lambda function to be executed when the button is clicked.
 */
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

    val animatedNormalColor by getAnimatedColor(
        enabled = enabled,
        activeColor = normalColor,
        inactiveColor = BorderGray,
    )
    val animatedPressedColor by getAnimatedColor(
        enabled = enabled,
        activeColor = pressedColor,
        inactiveColor = DarkBorderGray,
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
                        .offset(y = getOffset(isPressed, offset))
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
                            y = getOffset(isPressed, offset)
                        )
                )
            }

        }
    }
}

/**
 * Returns the animated color based on whether the button is enabled or not.
 *
 * @param enabled A boolean indicating whether the button is enabled.
 * @param activeColor The color to use when the button is enabled.
 * @param inactiveColor The color to use when the button is disabled.
 * @param easing The easing curve for the animation (default is FastOutSlowInEasing).
 * @return The animated color state.
 */
@Composable
private fun getAnimatedColor(
    enabled: Boolean,
    activeColor: Color,
    inactiveColor: Color,
    easing: Easing = FastOutSlowInEasing
) = animateColorAsState(
    targetValue = if (enabled) activeColor else inactiveColor,
    animationSpec = tween(
        durationMillis = 500,
        easing = easing
    )
)

/**
 * Returns the offset value for the icon or text when the button is pressed.
 *
 * @param isPressed A boolean indicating whether the button is pressed.
 * @param offset The value to offset the position of the icon or text.
 * @return The offset value.
 */
private fun getOffset(isPressed: Boolean, offset: Dp) =
    if (isPressed) Dimens.Zero else (-offset / 2)