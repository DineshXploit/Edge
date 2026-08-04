package com.righttofitness.ai.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NeonGreen = Color(0xFF65FFB8); val NeonBlue = Color(0xFF64D2FF); val Space = Color(0xFF050714); val Glass = Color(0x331B2440)
private val scheme = darkColorScheme(primary = NeonGreen, secondary = NeonBlue, background = Space, surface = Color(0xFF0D1224), onPrimary = Color.Black, onBackground = Color.White)
@Composable fun RtfTheme(content: @Composable () -> Unit) = MaterialTheme(colorScheme = scheme, typography = Typography(), content = content)
