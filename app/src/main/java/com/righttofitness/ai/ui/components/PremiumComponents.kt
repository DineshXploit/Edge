package com.righttofitness.ai.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.righttofitness.ai.ui.theme.Glass
import com.righttofitness.ai.ui.theme.NeonBlue
import com.righttofitness.ai.ui.theme.NeonGreen

@Composable
fun GlassCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.linearGradient(
                    listOf(Glass, MaterialTheme.colorScheme.surface.copy(alpha = 0.72f)),
                ),
            )
            .padding(20.dp),
        content = content,
    )
}

@Composable
fun ProgressRing(label: String, progress: Float, value: String, modifier: Modifier = Modifier) {
    val animated by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(900),
        label = "ring",
    )
    Box(modifier.size(118.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            drawCircle(NeonBlue.copy(alpha = 0.16f), style = Stroke(12.dp.toPx()))
            drawArc(
                brush = Brush.sweepGradient(listOf(NeonGreen, NeonBlue, NeonGreen)),
                startAngle = -90f,
                sweepAngle = animated * 360f,
                useCenter = false,
                style = Stroke(12.dp.toPx(), cap = StrokeCap.Round),
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(value, style = MaterialTheme.typography.titleLarge)
            Text(label, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun HeroBackdrop() {
    Canvas(Modifier.fillMaxSize()) {
        drawCircle(NeonBlue.copy(alpha = 0.18f), radius = size.minDimension * 0.42f, center = Offset(size.width * 0.85f, size.height * 0.05f))
        drawCircle(NeonGreen.copy(alpha = 0.12f), radius = size.minDimension * 0.35f, center = Offset(0f, size.height * 0.28f))
    }
}
