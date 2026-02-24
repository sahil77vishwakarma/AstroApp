package com.capital.motion.clotho.ui.commonComposable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import kotlin.math.*
import kotlin.random.Random

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF06060F)),
        contentAlignment = Alignment.Center,
    ) {
        // Layer 1 — galaxy stars blinking
        GalaxyStarsLayer()

        // Layer 2 — cosmic loader on top
        CosmicLoadingIndicator(
            variant = CosmicVariant.LIGHT,
            size    = 160.dp,
        )
    }
}


private data class GalaxyStar(
    val x: Float,               // 0f–1f screen fraction
    val y: Float,               // 0f–1f screen fraction
    val radius: Float,          // dot radius px
    val color: Color,           // star color type
    val phaseOffset: Float,     // 0f–1f blink phase offset
    val blinkSpeed: Float,      // multiplier — fast or slow blinker
    val hasGlow: Boolean,       // larger stars get a soft glow
)

// Real galaxies have:
//  - Lots of small white/blue stars
//  - A few warm yellow stars
//  - Rare large bright blue giants
//  - Occasional red/orange cool stars
private val STAR_PALETTE = listOf(
    Color(0xFFFFFFFF) to 0.40f,   // white       — 40% of stars
    Color(0xFFFFFFFF) to 0.25f,   // blue-white  — 25%
    Color(0xFFFFFFFF) to 0.15f,   // blue        — 15%
    Color(0xFFFFFFFF) to 0.12f,   // yellow      — 12%
    Color(0xFFFFFFFF) to 0.08f,   // orange-red  —  8%
)

private fun randomStarColor(): Color {
    val roll = Random.nextFloat()
    var cumulative = 0f
    for ((color, weight) in STAR_PALETTE) {
        cumulative += weight
        if (roll < cumulative) return color
    }
    return Color.White
}

// Generated once — stable across recompositions
private val GALAXY_STARS: List<GalaxyStar> = List(180) {
    val radius = Random.nextFloat() * 2.2f + 0.4f   // 0.4px – 2.6px
    GalaxyStar(
        x          = Random.nextFloat(),
        y          = Random.nextFloat(),
        radius     = radius,
        color      = randomStarColor(),
        phaseOffset = Random.nextFloat(),            // unique blink timing
        blinkSpeed  = Random.nextFloat() * 1.5f + 0.5f, // 0.5× – 2× speed
        hasGlow     = radius > 1.6f,                // only bigger stars glow
    )
}





// ═══════════════════════════════════════════════════════════
//  GALAXY STARS LAYER
// ═══════════════════════════════════════════════════════════

@Composable
fun GalaxyStarsLayer(modifier: Modifier = Modifier) {

    // Single master clock — drives ALL 180 stars cheaply
    val transition = rememberInfiniteTransition(label = "galaxy")
    val masterClock by transition.animateFloat(
        initialValue  = 0f,
        targetValue   = 1f,
        animationSpec = infiniteRepeatable(
            animation  = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "galaxy_clock",
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        GALAXY_STARS.forEach { star ->

            // Each star gets its own slice of the clock
            val localTime = ((masterClock * star.blinkSpeed) + star.phaseOffset) % 1f

            // Smooth sine-based pulse — more organic than triangle wave
            // sin goes -1→1, we remap to 0→1
            val sine  = (kotlin.math.sin(localTime * 2f * Math.PI.toFloat()) + 1f) / 2f
            // Keep a minimum brightness so stars never fully disappear
            val alpha = sine * 0.75f + 0.15f     // range 0.15 – 0.90

            val cx = star.x * size.width
            val cy = star.y * size.height

            // Soft glow halo for brighter stars
            if (star.hasGlow) {
                // Outer glow — very transparent
                drawCircle(
                    color  = star.color.copy(alpha = alpha * 0.15f),
                    radius = star.radius * 4f,
                    center = Offset(cx, cy),
                )
                // Inner glow
                drawCircle(
                    color  = star.color.copy(alpha = alpha * 0.35f),
                    radius = star.radius * 2f,
                    center = Offset(cx, cy),
                )
            }

            // Sharp star core
            drawCircle(
                color  = star.color.copy(alpha = alpha),
                radius = star.radius,
                center = Offset(cx, cy),
            )
        }
    }
}