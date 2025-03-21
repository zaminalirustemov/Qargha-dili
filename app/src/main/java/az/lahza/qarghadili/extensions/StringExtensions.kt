package az.lahza.qarghadili.extensions

/**
 * Returns an empty [String].
 *
 * This function provides a more readable and meaningful way to represent an empty string
 * (`""`). It can be used wherever an empty string is needed, improving code readability
 * and reducing the use of hardcoded values in your project.
 *
 * Example usage:
 * ```
 * val emptyString = String.empty()
 * binding.textView.text = String.empty()
 * ```
 *
 * @return An empty string.
 */
fun String.Companion.empty() = ""

/**
 * Extension function to check if the StringBuilder contains any vowels.
 *
 * This function iterates through the characters of the StringBuilder and checks if any character
 * is a vowel (using the provided `vowels` string).
 *
 * @param vowels A string containing the vowels to check against.
 * @return `true` if any character in the StringBuilder is a vowel, otherwise `false`.
 */
fun StringBuilder.containsAnyVowel(vowels: String) = this.any { it.isVowel(vowels) }