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
import az.lahza.qarghadili.ui.theme.Dimens

/**
 * Composable that renders action buttons for copy, speak, and send actions.
 *
 * @param crowLanguageText The text in Crow Language that enables or disables the buttons.
 * @param onCopyClick Lambda invoked when the copy button is clicked.
 * @param onSpeakClick Lambda invoked when the speak button is clicked.
 * @param onSendClick Lambda invoked when the send button is clicked.
 */
@Composable
fun ActionButtons(
    crowLanguageText: String,
    onCopyClick: () -> Unit,
    onSpeakClick: () -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.ExtraLarge),
        horizontalArrangement = Arrangement.spacedBy(Dimens._16DP),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CrownLanguageButton(
            modifier = Modifier.weight(0.2f),
            icon = R.drawable.ic_file_copy,
            onClick = onCopyClick,
            enabled = crowLanguageText.isNotBlank()
        )

        CrownLanguageButton(
            modifier = Modifier.weight(0.2f),
            icon = R.drawable.baseline_mic_none_24,
            onClick = onSpeakClick,
            enabled = crowLanguageText.isNotBlank()
        )

        CrownLanguageButton(
            modifier = Modifier.weight(0.6f),
            text = stringResource(R.string.send_button_label),
            fontSize = Dimens._16SP,
            onClick = onSendClick,
            enabled = crowLanguageText.isNotBlank()
        )
    }
}