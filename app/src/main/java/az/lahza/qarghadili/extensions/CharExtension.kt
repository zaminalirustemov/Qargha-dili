package az.lahza.qarghadili.extensions

/**
 * Extension function to check if a character is a vowel.
 *
 * This function checks if the current character (`this`) exists in the provided string of vowels.
 * The vowels string can contain both uppercase and lowercase vowels for different languages.
 *
 * @param vowels A string containing vowels to check against.
 * @return `true` if the character is in the list of vowels, otherwise `false`.
 */
fun Char.isVowel(vowels: String): Boolean = this in vowels
