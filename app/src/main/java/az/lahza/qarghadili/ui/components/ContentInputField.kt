package az.lahza.qarghadili.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import az.lahza.qarghadili.R
import az.lahza.qarghadili.ui.theme.AccentGold
import az.lahza.qarghadili.ui.theme.BorderGray
import az.lahza.qarghadili.ui.theme.Dimens
import az.lahza.qarghadili.ui.theme.TextColor

/**
 * @param content The current value of the input field.
 * @param onValueChange Lambda function that is called when the input field value changes.
 *                     It updates the parent state with the new content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentInputField(content: String, onValueChange: (String) -> Unit) {
    val titleTextStyle = TextStyle(
        fontSize = Dimens._20SP,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = AccentGold
    )

    val labelTextStyle = TextStyle(
        fontSize = Dimens._14SP,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = TextColor
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.qargha_dili_generator),
                modifier = Modifier.padding(bottom = Dimens._24DP),
                style = titleTextStyle,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.qargha_dili_aciqlamasi),
                modifier = Modifier.padding(bottom = Dimens.MediumLarge),
                style = labelTextStyle,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(Dimens._20DP))

        Text(
            text = stringResource(R.string.metn_daxil_edin),
            modifier = Modifier.padding(bottom = Dimens.MediumLarge),
            style = labelTextStyle,
        )

        OutlinedTextField(
            value = content,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens._150DP),
            shape = RoundedCornerShape(Dimens.ExtraLarge),
            textStyle = labelTextStyle,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text // Make sure to use a text keyboard
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AccentGold,
                unfocusedBorderColor = BorderGray,
                focusedLabelColor = AccentGold,
                unfocusedLabelColor = TextColor,
                cursorColor = AccentGold,
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor
            )
        )
    }
}
