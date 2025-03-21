package az.lahza.qarghadili.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import az.lahza.qarghadili.R
import az.lahza.qarghadili.extensions.empty
import az.lahza.qarghadili.ui.components.ContentInputField
import az.lahza.qarghadili.ui.components.CrownLanguageButton
import az.lahza.qarghadili.ui.components.snack.AlertSnack
import az.lahza.qarghadili.ui.components.snack.rememberSnackState
import az.lahza.qarghadili.ui.theme.AccentGold
import az.lahza.qarghadili.ui.theme.AccentOrange
import az.lahza.qarghadili.ui.theme.BorderGray
import az.lahza.qarghadili.ui.theme.DarkAccentOrange
import az.lahza.qarghadili.ui.theme.DarkBackground
import az.lahza.qarghadili.ui.theme.DarkBorderGray
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
            color = DarkBackground, darkIcons = false
        )
    }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // State management
    var content by remember { mutableStateOf(String.empty()) }
    var customLanguage by remember { mutableStateOf(String.empty()) }
    // Initialize crowLanguageText
    var crowLanguageText by remember { mutableStateOf(String.empty()) }


    var qaButtonClicked by remember { mutableStateOf(true) }
    var zaButtonClicked by remember { mutableStateOf(false) }
    var customButtonClicked by remember { mutableStateOf(false) }

    val errorSnackState = rememberSnackState()
    val successSnackState = rememberSnackState()

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.ExtraLarge),
                    horizontalArrangement = Arrangement.spacedBy(Dimens._16DP),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CrownLanguageButton(modifier = Modifier.weight(0.5f),
                        normalColor = if (qaButtonClicked) AccentOrange else BorderGray,
                        pressedColor = if (qaButtonClicked) DarkAccentOrange else DarkBorderGray,
                        text = stringResource(R.string.qa_qe),
                        fontSize = 16.sp,
                        onClick = {
                            qaButtonClicked = true
                            zaButtonClicked = false
                            customButtonClicked = false
                        })

                    CrownLanguageButton(modifier = Modifier.weight(0.5f),
                        normalColor = if (zaButtonClicked) AccentOrange else BorderGray,
                        pressedColor = if (zaButtonClicked) DarkAccentOrange else DarkBorderGray,
                        text = stringResource(R.string.za_ze),
                        fontSize = 16.sp,
                        onClick = {
                            zaButtonClicked = true
                            qaButtonClicked = false
                            customButtonClicked = false
                        })
                    CrownLanguageButton(modifier = Modifier.weight(1f),
                        normalColor = if (customButtonClicked) AccentOrange else BorderGray,
                        pressedColor = if (customButtonClicked) DarkAccentOrange else DarkBorderGray,
                        text = stringResource(R.string.s_n_ist_y_n_olsun),
                        fontSize = 16.sp,
                        onClick = {
                            customButtonClicked = true
                            qaButtonClicked = false
                            zaButtonClicked = false
                        })
                }


                val labelTextStyle = TextStyle(
                    fontSize = Dimens._14SP,
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
                        onValueChange = { customLanguage = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimens._56DP),
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
                        ),
                        placeholder = {
                            Text(stringResource(R.string.bu_i_n_ad_ver_k_indi))
                        }
                    )
                }


                Text(
                    text = stringResource(R.string.evirilmi_m_tn),
                    modifier = Modifier.padding(vertical = Dimens.ExtraLarge),
                    style = labelTextStyle,
                )

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


            CrownLanguageButton(
                Modifier
                    .padding(vertical = Dimens._32DP, horizontal = Dimens._16DP)
            ) {
                if (content.isBlank()) {
                    errorSnackState.showSnack(context.getString(R.string.empty_content))
                } else {
                    crowLanguageText =
                        convertToCrowLanguage(
                            content,
                            customLanguage,
                            qaButtonClicked,
                            zaButtonClicked,
                            customButtonClicked
                        ) // Update the crowLanguageText
                }
            }

        }

        AlertSnack(
            state = errorSnackState, containerColor = Color.Red
        )

        AlertSnack(
            state = successSnackState, containerColor = MainBlue
        )
    }
}

fun convertToCrowLanguage(
    text: String,
    customText: String? = "q",
    qaButtonClicked: Boolean,
    zaButtonClicked: Boolean,
    customButtonClicked: Boolean
): String {
    // List of vowels in Azerbaijani language
    val vowels = "aeəoöuüıiAEOUƏÖÜIİ"

    // StringBuilder to hold the transformed text
    val crowLanguageText = StringBuilder()

    val appendString = when {
        qaButtonClicked -> "q"
        zaButtonClicked -> "z"
        customButtonClicked -> customText
        else -> "q"
    }

    // Iterate over each character in the text
    for (char in text) {
        if (char.isVowel(vowels)) {
            // For vowels, append 'q' before and after the vowel
            crowLanguageText.append(char).append(appendString).append(char)
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
