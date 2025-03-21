package az.lahza.qarghadili.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.lahza.qarghadili.R
import az.lahza.qarghadili.extensions.empty
import az.lahza.qarghadili.ui.components.ContentInputField
import az.lahza.qarghadili.ui.components.GenerateCrownLanguageButton
import az.lahza.qarghadili.ui.components.snack.AlertSnack
import az.lahza.qarghadili.ui.components.snack.rememberSnackState
import az.lahza.qarghadili.ui.theme.AccentGold
import az.lahza.qarghadili.ui.theme.BorderGray
import az.lahza.qarghadili.ui.theme.DarkBackground
import az.lahza.qarghadili.ui.theme.Dimens
import az.lahza.qarghadili.ui.theme.MainBlue
import az.lahza.qarghadili.ui.theme.TextColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateCrowLanguageScreen(innerPadding: PaddingValues) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = DarkBackground,
            darkIcons = false
        )
    }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // State management
    var content by remember { mutableStateOf(String.empty()) }
    // Initialize crowLanguageText
    var crowLanguageText by remember { mutableStateOf(String.empty()) }
    var backgroundColor by remember { mutableStateOf(Color.White) }

    val errorSnackState = rememberSnackState()
    val successSnackState = rememberSnackState()

    Box(modifier = Modifier
        .background(DarkBackground)
        .fillMaxSize()
        .padding(innerPadding)
        .pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus() })
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens._16DP)
        ) {
            ContentInputField(content, onValueChange = { content = it })


            OutlinedTextField(
                value = crowLanguageText,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens._150DP),
                shape = RoundedCornerShape(Dimens.ExtraLarge),
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
                ),
                readOnly = true
            )
        }

        AlertSnack(
            state = errorSnackState,
            containerColor = Color.Red
        )

        AlertSnack(
            state = successSnackState,
            containerColor = MainBlue
        )

        data class DuolingoButtonStyle(
            val normalColor: Color = Color(0xff00B3FD),
            val pressedColor: Color = Color(0xff009CDC),
            val textColor: Color = Color.White,
            val fontSize: TextUnit = 18.sp,
            val cornerRadius: Dp = 8.dp,
            val offset: Dp = 4.dp,
            val horizontalPadding: Dp = 40.dp,
            val verticalPadding: Dp = 16.dp,
            val customIcon: @Composable (() -> Unit)? = null
        )

        GenerateCrownLanguageButton(
            Modifier
                .padding(horizontal = Dimens._16DP, vertical = Dimens._32DP)
                .align(Alignment.BottomCenter),
        ) {
            if (content.isBlank()) {
                errorSnackState.showSnack(context.getString(R.string.empty_content))
            } else {
                crowLanguageText = convertToCrowLanguage(content) // Update the crowLanguageText
            }
        }

    }
}

fun convertToCrowLanguage(text: String): String {
    // List of vowels in Azerbaijani language
    val vowels = "aeəoöuüıiAEOUƏÖÜIİ"

    // StringBuilder to hold the transformed text
    val crowLanguageText = StringBuilder()

    // Iterate over each character in the text
    for (char in text) {
        if (char.isVowel(vowels)) {
            // For vowels, append 'q' before and after the vowel
            crowLanguageText.append(char).append("q").append(char)
        } else {
            // For consonants, append as is
            crowLanguageText.append(char)
        }
    }

    return crowLanguageText.toString()
}

// Extension function to check if a character is a vowel
fun Char.isVowel(vowels: String): Boolean {
    return this in vowels
}
