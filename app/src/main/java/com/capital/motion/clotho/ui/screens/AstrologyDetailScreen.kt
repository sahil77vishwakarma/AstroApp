package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.theme.ClothoTheme

// ─── Colors ───────────────────────────────────────────────────────────────────

private val TextBlack = Color(0xFF1A1A1A)
private val TextGrey = Color(0xFF888888)
private val TextLightGrey = Color(0xFFAAAAAA)
private val AccentPink = Color(0xFFE8837A)   // salmon/pink highlight color from screenshot
private val DividerGrey = Color(0xFFE0E0E0)
private val ChartBg = Color(0xFFF7F7F7)

// ─── Data Models ──────────────────────────────────────────────────────────────

data class AstrologyCardData(
    val title: String,
    val subtitle: String,
    val subtitleExtra: String = "",
    val credits: Int = 1,
    val isNew: Boolean = false,
    val userInfo: UserAstrologyInfo,
    val generatedAt: String = "",
    val sections: List<AstrologySection> = emptyList()
)

data class UserAstrologyInfo(
    val name: String = "YOU",
    val sunSign: String,
    val moonSign: String,
    val risingSign: String,
    val birthDate: String,
    val birthTime: String,
    val birthLocation: String
)

/**
 * [heading]   — bold section title (empty = no heading, just paragraph)
 * [body]      — paragraph text
 * [isHighlighted] — renders body in pink/accent color for key insights
 */
data class AstrologySection(
    val heading: String = "",
    val body: String,
    val isHighlighted: Boolean = false
)

// ─── Screen ───────────────────────────────────────────────────────────────────

@Composable
fun AstrologyDetailScreen(
    data: AstrologyCardData,
    onBack: () -> Unit,
    onClose: () -> Unit = onBack
) {
    val scrollState = rememberScrollState()

    val progress by remember {
        derivedStateOf {
            if (scrollState.maxValue == 0) 0f
            else scrollState.value.toFloat() / scrollState.maxValue.toFloat()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // ── Sticky Header ─────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextBlack,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onBack() }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = data.title,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = TextBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row {
                        Text(
                            text = data.subtitle,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.medium)),
                            color = TextGrey,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (data.subtitleExtra.isNotEmpty()) {
                            Text(
                                text = " · ${data.subtitleExtra}",
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.medium)),
                                color = TextGrey,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // − T +
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "−",
                        fontSize = 14.sp,
                        color = TextGrey,
                        modifier = Modifier.clickable { }.padding(horizontal = 3.dp)
                    )
                    Text(
                        text = "T",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        color = TextBlack
                    )
                    Text(
                        text = "+",
                        fontSize = 14.sp,
                        color = TextGrey,
                        modifier = Modifier.clickable { }.padding(horizontal = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = TextBlack,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onClose() }
                )
            }

            // Progress bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .weight(1f)
                        .height(2.5.dp)
                        .clip(RoundedCornerShape(50)),
                    color = TextBlack,
                    trackColor = DividerGrey
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${(progress * 100).toInt()}%",
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = TextLightGrey
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
        }

        // ── Scrollable body ───────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // YOU block
            Text(
                text = data.userInfo.name,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Sun in ${data.userInfo.sunSign} | Moon in ${data.userInfo.moonSign} | ${data.userInfo.risingSign} Rising",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                color = TextBlack,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Born ${data.userInfo.birthDate} at ${data.userInfo.birthTime} in ${data.userInfo.birthLocation}",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                color = TextBlack,
                lineHeight = 20.sp
            )

            if (data.generatedAt.isNotEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Generated: ${data.generatedAt}",
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = TextLightGrey
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // ── Natal chart ───────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .clip(RoundedCornerShape(50))   // circle
                        .border(1.dp, DividerGrey, RoundedCornerShape(50))
                        .background(ChartBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "☽",
                        fontSize = 48.sp,
                        color = TextLightGrey
                    )
                    // 👉 Replace with your actual chart image:
                    // Image(
                    //     painter = rememberAsyncImagePainter(chartImageUrl),
                    //     contentDescription = "Natal Chart",
                    //     modifier = Modifier.fillMaxSize(),
                    //     contentScale = ContentScale.Fit
                    // )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Reading sections ──────────────────────────────────────────────
            data.sections.forEachIndexed { index, section ->

                // Divider + heading for new titled sections
                if (section.heading.isNotEmpty()) {
                    if (index != 0) {
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = DividerGrey, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                    Text(
                        text = section.heading,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.medium)),
                        fontWeight = FontWeight.Bold,
                        color = TextBlack
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Body text — pink if highlighted, black otherwise
                Text(
                    text = section.body,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = if (section.isHighlighted) AccentPink else TextBlack,
                    lineHeight = 23.sp
                )

                Spacer(modifier = Modifier.height(14.dp))
            }

            // ── Birth chart grid (bottom of screen in screenshot) ─────────────
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = DividerGrey, thickness = 1.dp)
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Birth Chart",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(12.dp))

            BirthChartGrid()

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

// ─── Birth chart aspect grid (bottom table in screenshot) ─────────────────────

@Composable
private fun BirthChartGrid() {
    val planets = listOf("☉", "☽", "☿", "♀", "♂", "♃", "♄", "⛢", "♆", "♇")
    val cellSize = 28.dp
    val accentColors = listOf(
        Color(0xFFE8837A), Color(0xFF7AB8E8), Color(0xFF88CC88),
        Color(0xFFE8C87A), Color(0xFFB87AE8)
    )

    Column {
        // Lower-triangle grid
        planets.forEachIndexed { row, planet ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Row planet label
                Text(
                    text = planet,
                    fontSize = 12.sp,
                    color = TextGrey,
                    modifier = Modifier.width(20.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                // Cells
                for (col in 0 until row) {
                    Box(
                        modifier = Modifier
                            .size(cellSize)
                            .border(0.5.dp, DividerGrey),
                        contentAlignment = Alignment.Center
                    ) {
                        val dotColor = if ((row + col) % 3 == 0)
                            accentColors[(row + col) % accentColors.size]
                        else Color.Transparent
                        if (dotColor != Color.Transparent) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(dotColor)
                            )
                        }
                    }
                }
                // Diagonal label cell
                Box(
                    modifier = Modifier
                        .size(cellSize)
                        .background(Color(0xFFF0F0F0))
                        .border(0.5.dp, DividerGrey),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = planet, fontSize = 11.sp, color = TextBlack)
                }
            }
        }
        // Column planet labels at bottom
        Spacer(modifier = Modifier.height(2.dp))
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            planets.dropLast(1).forEach { p ->
                Box(
                    modifier = Modifier.size(cellSize),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = p, fontSize = 11.sp, color = TextGrey)
                }
            }
        }
    }
}

// ─── Preview ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AstrologyDetailScreenPreview() {
    ClothoTheme {
        AstrologyDetailScreen(
            data = AstrologyCardData(
                title = "Year Ahead",
                subtitle = "Solar Return Forecast",
                subtitleExtra = "From January ...",
                userInfo = UserAstrologyInfo(
                    sunSign = "Taurus",
                    moonSign = "Scorpio",
                    risingSign = "Leo",
                    birthDate = "January 15, 1990",
                    birthTime = "12:00 AM",
                    birthLocation = "London, UK"
                ),
                generatedAt = "February 23, 2026 at 05:48 PM (1 credit)",
                sections = listOf(
                    AstrologySection(
                        heading = "Your Year Ahead: The Solar Return",
                        body = "The Solar Return is one of the most powerful predictive tools in astrology, providing a detailed map of your upcoming year from birthday to birthday. This chart is cast for the exact moment the Sun returns to its natal position, creating a fresh annual cycle filled with new possibilities, challenges, and opportunities for growth."
                    ),
                    AstrologySection(
                        body = "Your Solar Return chart this year emphasizes themes of personal transformation and growth. The Ascendant of the Solar Return sets the overall tone for the year, colouring every experience with its particular energy and focus."
                    ),
                    AstrologySection(
                        body = "This year's Solar Return Ascendant carries a specific quality of energy that will shape how you approach new beginnings and how others perceive you during this cycle.",
                        isHighlighted = true
                    ),
                    AstrologySection(
                        heading = "Love and Relationships",
                        body = "Venus in fire signs brings passion and spontaneity. Venus in earth signs seeks stability and sensual pleasure. Venus in air signs values communication and intellectual connection. Venus in water signs craves emotional depth and soul bonding."
                    ),
                    AstrologySection(
                        body = "The aspects Venus makes reveal how easily love flows this year. Venus trine Jupiter is one of the most fortunate aspects for romance, bringing expansion and joy in love. Venus square Saturn may indicate delays or obstacles that ultimately strengthen commitment."
                    ),
                    AstrologySection(
                        body = "Mars in your Solar Return shows how you pursue what you desire, including romantic interests. Mars in the Fifth House often indicates active romantic pursuit, while Mars in the Seventh can bring both passion and conflict in partnerships."
                    ),
                    AstrologySection(
                        body = "The Eighth House governs intimacy, sexuality, and deep emotional bonding. Planets here intensify the depth of your relating.",
                        isHighlighted = true
                    ),
                    AstrologySection(
                        heading = "Career and Financial Outlook",
                        body = "The Tenth House of your Solar Return governs career matters and public reputation. The planets placed here and the aspects they form indicate the professional opportunities and challenges you will encounter this year."
                    ),
                    AstrologySection(
                        body = "Your financial picture is revealed through the Second House (personal resources and income) and the Eighth House (shared resources, investments, and transformation of values)."
                    )
                )
            ),
            onBack = {}
        )
    }
}