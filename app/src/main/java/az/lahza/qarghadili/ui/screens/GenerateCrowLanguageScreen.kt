package az.lahza.qarghadili.ui.screens

import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import az.lahza.qarghadili.R
import az.lahza.qarghadili.enums.ButtonState
import az.lahza.qarghadili.extensions.empty
import az.lahza.qarghadili.ui.components.ActionButtons
import az.lahza.qarghadili.ui.components.ContentInputField
import az.lahza.qarghadili.ui.components.ConvertedTextOutput
import az.lahza.qarghadili.ui.components.CrownLanguageButton
import az.lahza.qarghadili.ui.components.CustomLanguageInput
import az.lahza.qarghadili.ui.components.LanguageButtons
import az.lahza.qarghadili.ui.components.SystemUiSetup
import az.lahza.qarghadili.ui.components.snack.AlertSnack
import az.lahza.qarghadili.ui.components.snack.rememberSnackState
import az.lahza.qarghadili.ui.theme.AccentOrange
import az.lahza.qarghadili.ui.theme.DarkBackground
import az.lahza.qarghadili.ui.theme.Dimens
import az.lahza.qarghadili.utils.convertToCrowLanguage
import az.lahza.qarghadili.utils.convertToTurkish
import kotlinx.coroutines.delay
import java.util.Locale

/**
 * Composable function that displays the user interface for generating and converting text into Crow language.
 * It handles the input of the original content, displays language options, and provides actions to copy, speak,
 * or share the converted text. The screen also includes input validation and displays error or success snack bars
 * based on user interactions.
 *
 * @param innerPadding Padding values passed from the parent composable to adjust screen content layout.
 */
@Composable
fun GenerateCrowLanguageScreen(innerPadding: PaddingValues) {
    // State management
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    var content by remember { mutableStateOf(String.empty()) }
    var customLanguage by remember { mutableStateOf(String.empty()) }
    var crowLanguageText by remember { mutableStateOf(String.empty()) }

    var buttonState by remember { mutableStateOf(ButtonState.QA) }

    var buttonClicked by remember { mutableStateOf(false) }

    val errorSnackState = rememberSnackState()
    val successSnackState = rememberSnackState()

    // Initialize Text-to-Speech
    val textToSpeech = remember { TextToSpeech(context) {} }
    textToSpeech.language = Locale("tr", "TR")

    // Set System UI
    SystemUiSetup()

    Box(
        modifier = Modifier
            .background(DarkBackground)
            .padding(innerPadding)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens._16DP)
            ) {
                ContentInputField(content, onValueChange = { content = it })

                LanguageButtons(
                    buttonState = buttonState,
                    onQaClick = { buttonState = ButtonState.QA },
                    onZaClick = { buttonState = ButtonState.ZA },
                    onCustomClick = { buttonState = ButtonState.CUSTOM }
                )

                CustomLanguageInput(
                    customLanguage = customLanguage,
                    customButtonClicked = buttonState == ButtonState.CUSTOM,
                    onValueChange = { customLanguage = it }
                )

                if (buttonClicked) {
                    // Change color temporarily for 1 second
                    LaunchedEffect(Unit) {
                        delay(1000)  // 1 second delay
                        buttonClicked = false  // Reset the state after 1 second
                    }
                }

                ConvertedTextOutput(
                    crowLanguageText = crowLanguageText,
                    buttonClicked = buttonClicked
                )

                ActionButtons(
                    crowLanguageText = crowLanguageText,
                    onCopyClick = {
                        buttonClicked = true
                        if (crowLanguageText.isBlank()) {
                            errorSnackState.showSnack(context.getString(R.string.no_converted_text_error))
                        } else {
                            clipboardManager.setText(AnnotatedString(crowLanguageText))
                            successSnackState.showSnack(context.getString(R.string.copy_success_message))
                        }
                    },
                    onSpeakClick = {
                        buttonClicked = true
                        if (crowLanguageText.isBlank()) {
                            errorSnackState.showSnack(context.getString(R.string.no_converted_text_error))
                        } else {
                            val convertedText = convertToTurkish(crowLanguageText)

                            textToSpeech.speak(
                                convertedText,
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                null
                            )
                        }
                    },
                    onSendClick = {
                        buttonClicked = true
                        if (crowLanguageText.isBlank()) {
                            errorSnackState.showSnack(context.getString(R.string.no_converted_text_error))
                        } else {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, crowLanguageText)
                                type = "text/plain"
                            }
                            context.startActivity(
                                Intent.createChooser(
                                    shareIntent,
                                    "Share via"
                                )
                            )

                        }
                    }
                )

                DisposableEffect(Unit) {
                    onDispose {
                        textToSpeech.stop()
                        textToSpeech.shutdown()
                    }
                }
            }


            CrownLanguageButton(
                Modifier
                    .padding(vertical = Dimens._32DP, horizontal = Dimens._16DP)
            ) {
                if (content.isBlank()) {
                    errorSnackState.showSnack(context.getString(R.string.empty_content_error))
                    crowLanguageText = String.empty()
                } else {
                    crowLanguageText =
                        convertToCrowLanguage(
                            content,
                            customLanguage,
                            buttonState
                        ) {
                            errorSnackState.showSnack(context.getString(R.string.no_vowels_error))
                        }
                }
            }

        }

        AlertSnack(
            state = errorSnackState, containerColor = Color.Red
        )

        AlertSnack(
            state = successSnackState, containerColor = AccentOrange
        )
    }
}
