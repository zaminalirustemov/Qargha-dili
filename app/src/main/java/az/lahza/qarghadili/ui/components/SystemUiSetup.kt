package az.lahza.qarghadili.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import az.lahza.qarghadili.ui.theme.DarkBackground
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Composable function that sets up the system UI bars (status bar and navigation bar) with a custom color.
 * It uses `rememberSystemUiController` to change the system bars' color to a dark background and ensures the icons
 * remain light for contrast.
 *
 * @see rememberSystemUiController
 * @see SideEffect
 */
@Composable
fun SystemUiSetup() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = DarkBackground, darkIcons = false
        )
    }
}