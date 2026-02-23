package com.capital.motion.clotho.ui.screens

import androidx.lifecycle.ViewModel
import com.capital.motion.clotho.ui.screens.AstrologyCardData
import com.capital.motion.clotho.ui.screens.AstrologySection
import com.capital.motion.clotho.ui.screens.UserAstrologyInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AstrologyViewModel : ViewModel() {

    private val _selectedCard = MutableStateFlow<AstrologyCardData?>(null)
    val selectedCard: StateFlow<AstrologyCardData?> = _selectedCard

    fun selectCard(card: AstrologyCardData) {
        _selectedCard.value = card
    }

    fun clearCard() {
        _selectedCard.value = null
    }
}

// ─── Card data factory ────────────────────────────────────────────────────────
// Call these to build the AstrologyCardData for each card type

fun buildYourPatternData(userInfo: UserAstrologyInfo): AstrologyCardData =
    AstrologyCardData(
        title = "Your Pattern",
        subtitle = "Natal Chart Analysis",
        subtitleExtra = "All time",
        credits = 5,
        isNew = true,
        userInfo = userInfo,
        generatedAt = "February 23, 2026 at 05:48 PM (5 credits)",
        sections = listOf(
            AstrologySection(
                heading = "Your Cosmic Blueprint",
                body = "Your natal chart is a snapshot of the sky at the exact moment of your birth. It reveals your personality, strengths, challenges, and the unique path your soul has chosen for this lifetime."
            ),
            AstrologySection(
                body = "The positions of the planets at your birth reveal deep truths about your character and destiny. Each placement carries its own meaning, and together they form a complete picture of who you are.",
                isHighlighted = true
            ),
            AstrologySection(
                heading = "Sun, Moon & Rising",
                body = "Your Sun sign represents your core identity and conscious ego. Your Moon sign governs your emotional inner world and instinctual responses. Your Rising sign shapes how others perceive you and how you approach new situations."
            )
        )
    )

fun buildMonthlyCycleData(userInfo: UserAstrologyInfo, cycle: String): AstrologyCardData =
    AstrologyCardData(
        title = "Monthly Cycle",
        subtitle = "Lunar Return Forecast",
        subtitleExtra = cycle,
        credits = 5,
        isNew = false,
        userInfo = userInfo,
        generatedAt = "February 23, 2026 at 05:48 PM (5 credits)",
        sections = listOf(
            AstrologySection(
                heading = "Your Emotional Landscape",
                body = "The Lunar Return occurs each month when the Moon returns to the exact degree it occupied at your birth. This chart reveals the emotional themes and inner experiences that will colour your month ahead."
            ),
            AstrologySection(
                body = "Pay attention to your intuition this cycle. The Moon's house placement in your Lunar Return indicates where your emotional focus will be concentrated.",
                isHighlighted = true
            ),
            AstrologySection(
                heading = "Key Themes This Month",
                body = "Relationships and communication come to the forefront this cycle. You may find yourself drawn to meaningful conversations and deeper connections. Trust what your heart is telling you."
            )
        )
    )