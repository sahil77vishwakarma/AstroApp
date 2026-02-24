package com.capital.motion.clotho.ui.commonComposable

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capital.motion.clotho.ui.theme.ClothoTheme
import kotlin.math.*

//  ENUM

enum class CosmicVariant { LIGHT, DARK }

// ═══════════════════════════════════════════════════════════
//  CONSTANTS
// ═══════════════════════════════════════════════════════════

private const val TOTAL_PHASES          = 120
private const val ANIMATION_DURATION_MS = 120 * 22   // 2 640 ms per full loop

private const val CENTER       = 60f
private const val OUTER_RADIUS = 54f
private const val INNER_RADIUS = 42f
private const val CORE_RADIUS  = 28f

private val PLANET_SYMBOLS = listOf("☉", "☽", "☿", "♀", "♂", "♃", "♄", "⛢", "♆", "♇")

// ═══════════════════════════════════════════════════════════
//  GEOMETRY
// ═══════════════════════════════════════════════════════════

private data class Line2D(val x1: Float, val y1: Float, val x2: Float, val y2: Float)
private data class Pt2D(val x: Float, val y: Float)
private data class PlanetPt(val symbol: String, val x: Float, val y: Float)
private data class AspectLine(
    val x1: Float, val y1: Float,
    val x2: Float, val y2: Float,
    val weight: Float,
)

private val HOUSE_LINES: List<Line2D> = List(12) { i ->
    val a = (i * 30 - 90) * (PI / 180).toFloat()
    Line2D(
        CENTER + cos(a) * CORE_RADIUS,  CENTER + sin(a) * CORE_RADIUS,
        CENTER + cos(a) * INNER_RADIUS, CENTER + sin(a) * INNER_RADIUS,
    )
}

private val ZODIAC_POSITIONS: List<Pt2D> = List(12) { i ->
    val a = (i * 30 + 15 - 90) * (PI / 180).toFloat()
    val r = (OUTER_RADIUS + INNER_RADIUS) / 2f
    Pt2D(CENTER + cos(a) * r, CENTER + sin(a) * r)
}

private val PLANET_POSITIONS: List<PlanetPt> = PLANET_SYMBOLS.mapIndexed { i, sym ->
    val a = ((i * 36 + i * 7) - 90) * (PI / 180).toFloat()
    val r = CORE_RADIUS - 8f - (i % 3) * 3f
    PlanetPt(sym, CENTER + cos(a) * r, CENTER + sin(a) * r)
}

private val ASPECT_LINES: List<AspectLine> = listOf(
    Triple(0, 4, 1.5f), Triple(1, 5, 1.0f), Triple(2, 7, 1.2f),
    Triple(3, 8, 0.8f), Triple(0, 6, 1.0f), Triple(4, 9, 0.8f),
).map { (f, t, w) ->
    AspectLine(
        PLANET_POSITIONS[f].x, PLANET_POSITIONS[f].y,
        PLANET_POSITIONS[t].x, PLANET_POSITIONS[t].y, w,
    )
}

// ═══════════════════════════════════════════════════════════
//  EASING
// ═══════════════════════════════════════════════════════════

private fun easeInOut(t: Float) = if (t < 0.5f) 2f * t * t else -1f + (4f - 2f * t) * t
private fun clamp01(v: Float)   = v.coerceIn(0f, 1f)

// ═══════════════════════════════════════════════════════════
//  COLORS
// ═══════════════════════════════════════════════════════════

private data class CosmicColors(
    val outer: Color,       val inner: Color,        val core: Color,
    val house: Color,       val zodiac: Color,        val aspect: Color,
    val planetBg: Color,    val planetStroke: Color,
    val planetText: Color,  val center: Color,
)

private fun cosmicColors(variant: CosmicVariant) = if (variant == CosmicVariant.DARK) {
    CosmicColors(
        outer        = Color(0xCC000000), inner        = Color(0x66000000),
        core         = Color(0x33000000), house        = Color(0x40000000),
        zodiac       = Color(0xB3000000), aspect       = Color(0x99000000),
        planetBg     = Color(0x80FFFFFF), planetStroke = Color(0x66000000),
        planetText   = Color(0xE6000000), center       = Color(0x80000000),
    )
} else {
    CosmicColors(
        outer        = Color(0xCC000000), inner        = Color(0x66494949),
        core         = Color(0xCC000000), house        = Color(0xCC000000),
        zodiac       = Color(0xB3000000), aspect       = Color(0xFF000000),
        planetBg     = Color(0xFFE3E3E3), planetStroke = Color(0xFF363535),
        planetText   = Color(0xFF000000), center       = Color(0x80FFFFFF),
    )
}

// ═══════════════════════════════════════════════════════════
//  MAIN COMPOSABLE  (animated)
// ═══════════════════════════════════════════════════════════

@Composable
fun CosmicLoadingIndicator(
    modifier: Modifier = Modifier,
    variant: CosmicVariant = CosmicVariant.LIGHT,
    size: Dp = 120.dp,
) {
    val transition = rememberInfiniteTransition(label = "cosmic_transition")
    val rawPhase by transition.animateFloat(
        initialValue  = 0f,
        targetValue   = TOTAL_PHASES.toFloat(),
        animationSpec = infiniteRepeatable(
            animation  = tween(durationMillis = ANIMATION_DURATION_MS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "cosmic_phase",
    )

    CosmicLoadingIndicatorStatic(
        phase    = rawPhase % TOTAL_PHASES,
        modifier = modifier,
        variant  = variant,
        size     = size,
    )
}

// ═══════════════════════════════════════════════════════════
//  STATIC COMPOSABLE  (for @Preview + tests)
// ═══════════════════════════════════════════════════════════

@Composable
fun CosmicLoadingIndicatorStatic(
    phase: Float,
    modifier: Modifier = Modifier,
    variant: CosmicVariant = CosmicVariant.LIGHT,
    size: Dp = 120.dp,
) {
    val p      = phase % TOTAL_PHASES
    val colors = cosmicColors(variant)

    val pOuter   = easeInOut(clamp01(p / 12f))
    val pInner   = easeInOut(clamp01((p - 6f)  / 12f))
    val pCore    = easeInOut(clamp01((p - 12f) / 10f))
    val pHouses  = easeInOut(clamp01((p - 20f) / 16f))
    val pZodiac  = easeInOut(clamp01((p - 30f) / 18f))
    val pPlanets = easeInOut(clamp01((p - 42f) / 20f))
    val pAspects = easeInOut(clamp01((p - 58f) / 50f))

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
            val scale = this.size.minDimension / 120f
            drawRings(colors, pOuter, pInner, pCore, scale)
            drawHouseLines(colors, pHouses, scale)
            drawZodiacDots(colors, pZodiac, scale)
            drawAspectLines(colors, pAspects, scale)
            drawPlanets(colors, pPlanets, scale)
            drawCenterDot(colors, pCore, scale)
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  DRAW HELPERS
// ═══════════════════════════════════════════════════════════

private fun DrawScope.drawRings(
    c: CosmicColors,
    pOuter: Float, pInner: Float, pCore: Float,
    scale: Float,
) {
    listOf(
        Triple(OUTER_RADIUS, pOuter, Pair(c.outer, 1.5f)),
        Triple(INNER_RADIUS, pInner, Pair(c.inner, 0.75f)),
        Triple(CORE_RADIUS,  pCore,  Pair(c.core,  0.5f)),
    ).forEach { (radius, progress, cw) ->
        if (progress <= 0f) return@forEach
        val (color, sw) = cw
        rotate(degrees = -90f, pivot = Offset(CENTER * scale, CENTER * scale)) {
            drawArc(
                color      = color,
                startAngle = 0f,
                sweepAngle = 360f * progress,
                useCenter  = false,
                topLeft    = Offset((CENTER - radius) * scale, (CENTER - radius) * scale),
                size       = Size(radius * 2f * scale, radius * 2f * scale),
                style      = Stroke(width = sw * scale, cap = StrokeCap.Butt),
            )
        }
    }
}

private fun DrawScope.drawHouseLines(c: CosmicColors, pHouses: Float, scale: Float) {
    HOUSE_LINES.forEachIndexed { i, l ->
        val t = clamp01(pHouses * 12f - i)
        if (t <= 0f) return@forEachIndexed
        drawLine(
            color       = c.house,
            start       = Offset(l.x1 * scale, l.y1 * scale),
            end         = Offset(
                (l.x1 + (l.x2 - l.x1) * t) * scale,
                (l.y1 + (l.y2 - l.y1) * t) * scale,
            ),
            strokeWidth = 0.5f * scale,
        )
    }
}

private fun DrawScope.drawZodiacDots(c: CosmicColors, pZodiac: Float, scale: Float) {
    ZODIAC_POSITIONS.forEachIndexed { i, z ->
        val alpha = clamp01(pZodiac * 12f - i)
        if (alpha <= 0f) return@forEachIndexed
        drawCircle(
            color  = c.zodiac.copy(alpha = alpha),
            radius = 1.5f * scale,
            center = Offset(z.x * scale, z.y * scale),
        )
    }
}

private fun DrawScope.drawAspectLines(c: CosmicColors, pAspects: Float, scale: Float) {
    ASPECT_LINES.forEachIndexed { i, l ->
        val t = clamp01(pAspects * 6f - i * 0.8f)
        if (t <= 0f) return@forEachIndexed
        drawLine(
            color       = c.aspect,
            start       = Offset(l.x1 * scale, l.y1 * scale),
            end         = Offset(
                (l.x1 + (l.x2 - l.x1) * t) * scale,
                (l.y1 + (l.y2 - l.y1) * t) * scale,
            ),
            strokeWidth = l.weight * scale,
            cap         = StrokeCap.Round,
        )
    }
}

private fun DrawScope.drawPlanets(c: CosmicColors, pPlanets: Float, scale: Float) {
    val paint = android.graphics.Paint().apply {
        textAlign   = android.graphics.Paint.Align.CENTER
        textSize    = 5f * scale
        isAntiAlias = true
    }
    PLANET_POSITIONS.forEachIndexed { i, pnt ->
        val alpha = clamp01(pPlanets * 10f - i)
        if (alpha <= 0f) return@forEachIndexed
        drawCircle(
            color  = c.planetBg.copy(alpha = alpha * c.planetBg.alpha),
            radius = 4f * scale,
            center = Offset(pnt.x * scale, pnt.y * scale),
        )
        drawCircle(
            color  = c.planetStroke.copy(alpha = alpha * c.planetStroke.alpha),
            radius = 4f * scale,
            center = Offset(pnt.x * scale, pnt.y * scale),
            style  = Stroke(width = 0.5f * scale),
        )
        paint.color = android.graphics.Color.argb(
            (alpha * 255 * c.planetText.alpha).toInt(),
            (c.planetText.red   * 255).toInt(),
            (c.planetText.green * 255).toInt(),
            (c.planetText.blue  * 255).toInt(),
        )
        drawContext.canvas.nativeCanvas.drawText(
            pnt.symbol,
            pnt.x * scale,
            (pnt.y + 1.8f) * scale,
            paint,
        )
    }
}

private fun DrawScope.drawCenterDot(c: CosmicColors, pCore: Float, scale: Float) {
    if (pCore <= 0f) return
    drawCircle(
        color  = c.center.copy(alpha = pCore * c.center.alpha),
        radius = 2f * scale,
        center = Offset(CENTER * scale, CENTER * scale),
    )
}

// ═══════════════════════════════════════════════════════════
//  @PREVIEW — PHASES
// ═══════════════════════════════════════════════════════════

@Preview(name = "① Phase 0 — Start",          widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun P0()       { PreviewWrapper(phase = 0f,   variant = CosmicVariant.LIGHT) }

@Preview(name = "② Phase 10 — Outer Ring",    widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun P10()      { PreviewWrapper(phase = 10f,  variant = CosmicVariant.LIGHT) }

@Preview(name = "③ Phase 22 — 3 Rings",       widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun P22()      { PreviewWrapper(phase = 22f,  variant = CosmicVariant.LIGHT) }

@Preview(name = "④ Phase 40 — Houses",        widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun P40()      { PreviewWrapper(phase = 40f,  variant = CosmicVariant.LIGHT) }

@Preview(name = "⑤ Phase 65 — Planets",       widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun P65()      { PreviewWrapper(phase = 65f,  variant = CosmicVariant.LIGHT) }

@Preview(name = "⑥ Phase 100 — Full (Light)", widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun P100L()    { PreviewWrapper(phase = 100f, variant = CosmicVariant.LIGHT) }

@Preview(name = "⑦ Phase 100 — Full (Dark)",  widthDp = 160, heightDp = 160, showBackground = true, backgroundColor = 0xFFF5F0E8)
@Composable private fun P100D()    { PreviewWrapper(phase = 100f, variant = CosmicVariant.DARK) }

// ═══════════════════════════════════════════════════════════
//  @PREVIEW — SIZES
// ═══════════════════════════════════════════════════════════

@Preview(name = "Size 80dp",  widthDp = 100, heightDp = 100, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun S80()  { PreviewWrapper(phase = 100f, variant = CosmicVariant.LIGHT, size = 80.dp) }

@Preview(name = "Size 120dp", widthDp = 140, heightDp = 140, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun S120() { PreviewWrapper(phase = 100f, variant = CosmicVariant.LIGHT, size = 120.dp) }

@Preview(name = "Size 180dp", widthDp = 200, heightDp = 200, showBackground = true, backgroundColor = 0xFF06060F)
@Composable private fun S180() { PreviewWrapper(phase = 100f, variant = CosmicVariant.LIGHT, size = 180.dp) }

// ═══════════════════════════════════════════════════════════
//  @PREVIEW — FULL SPLASH SCREEN  (uses ParticleSystemTheme)
// ═══════════════════════════════════════════════════════════

@Preview(name = "Splash — Dark",  widthDp = 360, heightDp = 640, showBackground = true, backgroundColor = 0xFF06060F)
@Composable
private fun PreviewSplashDark() {
    ClothoTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFF06060F)),
            contentAlignment = Alignment.Center,
        ) {
            CosmicLoadingIndicator(variant = CosmicVariant.LIGHT, size = 160.dp)
        }
    }
}

@Preview(name = "Splash — Light", widthDp = 360, heightDp = 640, showBackground = true, backgroundColor = 0xFFF5F0E8)
@Composable
private fun PreviewSplashLight() {
    ClothoTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF5F0E8)),
            contentAlignment = Alignment.Center,
        ) {
            CosmicLoadingIndicator(variant = CosmicVariant.DARK, size = 160.dp)
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  INTERNAL PREVIEW HELPER
// ═══════════════════════════════════════════════════════════

@Composable
private fun PreviewWrapper(phase: Float, variant: CosmicVariant, size: Dp = 120.dp) {
    ClothoTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CosmicLoadingIndicatorStatic(phase = phase, variant = variant, size = size)
        }
    }
}