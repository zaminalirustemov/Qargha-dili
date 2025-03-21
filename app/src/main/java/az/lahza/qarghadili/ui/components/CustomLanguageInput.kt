package az.lahza.qarghadili.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import az.lahza.qarghadili.R
import az.lahza.qarghadili.ui.theme.AccentGold
import az.lahza.qarghadili.ui.theme.BorderGray
import az.lahza.qarghadili.ui.theme.Dimens
import az.lahza.qarghadili.ui.theme.TextColor

/**
 * Composable function that displays an input field for the custom language, visible only when the custom button is clicked.
 * The text field has a sliding and fading animation when it appears or disappears.
 *
 * @param customLanguage The text that the user enters in the input field.
 * @param customButtonClicked A Boolean that determines whether the custom language input field is visible.
 * @param onValueChange A lambda function that handles changes to the custom language input value.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLanguageInput(
    customLanguage: String,
    customButtonClicked: Boolean,
    onValueChange: (String) -> Unit
) {
    val labelTextStyle = TextStyle(
        fontSize = Dimens._12SP,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = TextColor
    )

    AnimatedVisibility(
        visible = customButtonClicked,
        enter = fadeIn(animationSpec = tween(durationMillis = 700)) +
                slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(350)) +
                slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(400))
    ) {
        OutlinedTextField(
            value = customLanguage,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens._56DP),
            shape = RoundedCornerShape(Dimens.ExtraLarge),
            textStyle = labelTextStyle,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AccentGold,
                unfocusedBorderColor = BorderGray,
                focusedLabelColor = AccentGold,
                unfocusedLabelColor = TextColor,
                cursorColor = AccentGold,
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor
            ),
            placeholder = { Text(stringResource(R.string.name_task_prompt)) }
        )
    }
}
