package org.playlearn.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Plum,
    secondary = Mint
)

private val DarkColors = darkColorScheme(
    primary = PlumDark,
    secondary = MintDark
)

@Composable
fun AppTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
package org.playlearn.ui.theme

import android.content.Context
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import java.io.File

fun loadCustomFont(context: Context): FontFamily {
    val input = context.assets.open("fonts/CustomFont.ttf")
    val outFile = File(context.cacheDir, "CustomFont.ttf")
    input.copyTo(outFile.outputStream())
    val typeface = Typeface(outFile)
    return FontFamily(Font(typeface))
}
@Composable
fun AppTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val customFont = remember { loadCustomFont(context) }

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(defaultFontFamily = customFont),
        content = content
    )
}
@Composable
fun AppTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val customFont = remember { loadCustomFont(context) }

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(defaultFontFamily = customFont),
        content = content
    )
}
@Composable
fun WelcomeScreen() {
    Text(
        text = "Bem-vindo ao PlayLearn!",
        style = MaterialTheme.typography.titleLarge
    )
}
Button(onClick = { SoundPlayer.playClick(context) }) {
    Text("Clique aqui")
}
