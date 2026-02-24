package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.theme.Black
import com.capital.motion.clotho.ui.theme.ClothoTheme

// ─── Colors ───────────────────────────────────────────────────────────────────

private val TextBlack    = Color(0xFF1A1A1A)
private val TextGrey     = Color(0xFF888888)
private val TextLightGrey= Color(0xFFAAAAAA)
private val AccentPink   = Color(0xFFE8837A)
private val DividerGrey  = Color(0xFFE0E0E0)

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

    // True scroll-based progress — updates live as user scrolls
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
            .padding(vertical = 40.dp)
    ) {

        // Sticky header (does not scroll)
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

            // ── Progress bar — driven by scrollState ──────────────────────────
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

        // ── Scrollable content ────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)   // <-- this scrollState drives the progress bar above
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

            Spacer(modifier = Modifier.height(24.dp))

            // ── Natal chart image ─────────────────────────────────────────────
            // SIZE: 280x280dp circle — matches the round chart in the screenshot
            // Replace R.drawable.ic_natal_chart with your Figma export
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.astor_icon_detail), // 👈 replace with your Figma image
                    contentDescription = "Natal Chart",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(280.dp)                          // circle size matching screenshot
                        .clip(RoundedCornerShape(50))          // keeps it circular
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Reading sections ──────────────────────────────────────────────
            data.sections.forEachIndexed { index, section ->
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

                Text(
                    text = section.body,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    color = if (section.isHighlighted) AccentPink else TextBlack,
                    lineHeight = 23.sp
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            // ── Birth chart aspect grid image ─────────────────────────────────
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

            // SIZE: full width, square aspect ratio — matches the grid table in the screenshot
            // Replace R.drawable.ic_birth_chart_grid with your Figma export
            Image(
                painter = painterResource(id = R.drawable.birth_chart_img), // 👈 replace with your Figma image
                contentDescription = "Birth Chart Aspect Grid",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)                           // square, matching the grid proportions
            )

            Spacer(modifier = Modifier.height(60.dp))
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
                        body = "Your Solar Return chart this year emphasizes themes of personal transformation and growth. The Ascendant of the Solar Return sets the overall tone for the year."
                    ),
                    AstrologySection(
                        body = "This year's Solar Return Ascendant carries a specific quality of energy that will shape how you approach new beginnings and how others perceive you.",
                        isHighlighted = true
                    ),
                    AstrologySection(
                        heading = "Love and Relationships",
                        body = "Venus in fire signs brings passion and spontaneity. Venus in earth signs seeks stability and sensual pleasure. Venus in air signs values communication and intellectual connection."
                    ),
                    AstrologySection(
                        body = "The aspects Venus makes reveal how easily love flows this year. Venus trine Jupiter is one of the most fortunate aspects for romance, bringing expansion and joy in love.",
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