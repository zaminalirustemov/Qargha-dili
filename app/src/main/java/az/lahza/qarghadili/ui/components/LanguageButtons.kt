package az.lahza.qarghadili.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import az.lahza.qarghadili.R
import az.lahza.qarghadili.enums.ButtonState
import az.lahza.qarghadili.ui.theme.Dimens

/**
 * Composable function that displays three language buttons for different button states (QA, ZA, and CUSTOM).
 * Each button is enabled based on the current button state and triggers a corresponding action when clicked.
 *
 * @param buttonState The current state that determines which button is enabled.
 * @param onQaClick A lambda function that is called when the QA button is clicked.
 * @param onZaClick A lambda function that is called when the ZA button is clicked.
 * @param onCustomClick A lambda function that is called when the CUSTOM button is clicked.
 */
@Composable
fun LanguageButtons(
    buttonState: ButtonState,
    onQaClick: () -> Unit,
    onZaClick: () -> Unit,
    onCustomClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.ExtraLarge),
        horizontalArrangement = Arrangement.spacedBy(Dimens._16DP),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CrownLanguageButton(
            modifier = Modifier.weight(0.5f),
            enabled = buttonState == ButtonState.QA,
            text = stringResource(R.string.qa_qe_example),
            fontSize = Dimens._16SP,
            onClick = onQaClick
        )

        CrownLanguageButton(
            modifier = Modifier.weight(0.5f),
            enabled = buttonState == ButtonState.ZA,
            text = stringResource(R.string.za_ze_example),
            fontSize = Dimens._16SP,
            onClick = onZaClick
        )

        CrownLanguageButton(
            modifier = Modifier.weight(1f),
            enabled = buttonState == ButtonState.CUSTOM,
            text = stringResource(R.string.custom_button_prompt),
            fontSize = Dimens._16SP,
            onClick = onCustomClick
        )
    }
}
