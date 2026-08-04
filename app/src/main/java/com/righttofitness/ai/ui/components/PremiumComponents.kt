package com.righttofitness.ai.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.righttofitness.ai.ui.theme.*

@Composable fun GlassCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) = Column(modifier.clip(RoundedCornerShape(28.dp)).background(Brush.linearGradient(listOf(Glass, MaterialTheme.colorScheme.surface.copy(alpha = .72f)))).padding(20.dp), content = content)
@Composable fun ProgressRing(label: String, progress: Float, value: String, modifier: Modifier = Modifier) { val animated by animateFloatAsState(progress.coerceIn(0f,1f), tween(900), label="ring"); Box(modifier.size(118.dp)) { Canvas(Modifier.fillMaxSize()) { drawCircle(NeonBlue.copy(.16f), style=Stroke(12.dp.toPx())); drawArc(Brush.sweepGradient(listOf(NeonGreen, NeonBlue, NeonGreen)), -90f, animated*360f, false, style=Stroke(12.dp.toPx(), cap=StrokeCap.Round)) }; Column(Modifier.fillMaxSize(), verticalArrangement=Arrangement.Center) { Text(value, style=MaterialTheme.typography.titleLarge); Text(label, style=MaterialTheme.typography.labelMedium) } } }
@Composable fun HeroBackdrop() { Canvas(Modifier.fillMaxSize()) { drawCircle(NeonBlue.copy(.18f), radius=size.minDimension*.42f, center=Offset(size.width*.85f, size.height*.05f)); drawCircle(NeonGreen.copy(.12f), radius=size.minDimension*.35f, center=Offset(0f, size.height*.28f)) } }
