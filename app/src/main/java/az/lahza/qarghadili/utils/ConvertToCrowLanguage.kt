package az.lahza.qarghadili.utils

import az.lahza.qarghadili.enums.ButtonState
import az.lahza.qarghadili.extensions.containsAnyVowel
import az.lahza.qarghadili.extensions.isVowel

/**
 * Converts a given text to "Crow Language" based on the selected button state.
 *
 * The function processes the input text by checking each character. If a vowel is found,
 * it appends a specified string (based on the button state) before and after the vowel.
 * If no vowels are found, a callback function is triggered.
 *
 * @param text The input text to be converted.
 * @param customText The custom text to use for the conversion in the custom button state (default is "q").
 * @param buttonState The current button state which determines the conversion logic.
 * @param onNoVowelsFound A callback function to be invoked if no vowels are found in the text.
 * @return The transformed text in Crow Language.
 */
fun convertToCrowLanguage(
    text: String,
    customText: String? = "q",
    buttonState: ButtonState,
    onNoVowelsFound: (() -> Unit)? = null
): String {
    // List of vowels in Azerbaijani language
    val vowels = "aeəoöuüıiAEOUƏÖÜIİ"

    // StringBuilder to hold the transformed text
    val appendString = getAppendStringForButtonState(buttonState, customText)

    return text.fold(StringBuilder()) { crowLanguageText, char ->
        if (char.isVowel(vowels)) {
            crowLanguageText.append(char).append(appendString).append(char)
        } else {
            crowLanguageText.append(char)
        }
    }.also {
        if (!it.containsAnyVowel(vowels)) onNoVowelsFound?.invoke()
    }.toString()
}

/**
 * Returns the appropriate string to append for a given button state.
 *
 * @param buttonState The button state which influences the appended string.
 * @param customText The custom text to be used in the "CUSTOM" state (defaults to "q").
 * @return The string that should be appended to vowels in Crow Language conversion.
 */
private fun getAppendStringForButtonState(buttonState: ButtonState, customText: String?): String {
    return when (buttonState) {
        ButtonState.QA -> "q"
        ButtonState.ZA -> "z"
        ButtonState.CUSTOM -> customText ?: "q"
    }
}

/**
 * Converts the given Crow Language text to Turkish by replacing specific characters.
 *
 * This function replaces:
 * - "q" with "g"
 * - "ə" with "e"
 *
 * @param text The input text to be converted to Turkish.
 * @return The text converted into Turkish characters.
 */
fun convertToTurkish(text: String): String {
    return text.replace("q", "g")
        .replace("ə", "e")
}