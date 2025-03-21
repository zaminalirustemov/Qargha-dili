package az.lahza.qarghadili.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
 * Composable function that displays the converted Crow Language text in an outlined text field.
 *
 * @param crowLanguageText The text that has been converted to Crow Language, which is displayed inside the text field.
 * @param buttonClicked Boolean flag indicating if a button has been clicked, affecting the appearance of the text field (border color and label color).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertedTextOutput(crowLanguageText: String, buttonClicked: Boolean) {
    val labelTextStyle = TextStyle(
        fontSize = Dimens._12SP,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = TextColor
    )

    val borderColor = if (buttonClicked) AccentGold else BorderGray
    val labelColor = if (buttonClicked) AccentGold else TextColor

    Text(
        text = stringResource(R.string.converted_text_label),
        modifier = Modifier.padding(vertical = Dimens.ExtraLarge),
        style = labelTextStyle,
    )

    OutlinedTextField(
        value = crowLanguageText,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens._120DP),
        shape = RoundedCornerShape(Dimens.ExtraLarge),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = labelColor,
            cursorColor = AccentGold,
            focusedTextColor = TextColor,
            unfocusedTextColor = TextColor
        ),
        readOnly = true
    )
}
