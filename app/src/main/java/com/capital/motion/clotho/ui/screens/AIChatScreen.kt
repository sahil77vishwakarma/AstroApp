package com.capital.motion.clotho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capital.motion.clotho.R
import com.capital.motion.clotho.ui.theme.ClothoTheme
import kotlinx.coroutines.launch

// ─── Colors ───────────────────────────────────────────────────────────────────

private val BgDark        = Color(0xFF06060F)
val TextWhite     = Color(0xFFFFFFFF)
private val TextGrey      = Color(0xFF888888)
private val BubbleBorder  = Color(0xFF2A2A2A)
private val InputBg       = Color(0xFF111118)
private val InputBorder   = Color(0xFF2A2A2A)
private val ChipBg        = Color(0xFF0E0E1A)
private val ChipBorder    = Color(0xFF2A2A2A)
private val CreditsBg     = Color(0xFF1A1A2A)
private val SendBtnColor  = Color(0xFF333344)

// ─── Data model ───────────────────────────────────────────────────────────────

data class ChatMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: String = ""
)

// ─── Suggested prompts ────────────────────────────────────────────────────────

private val suggestedPrompts = listOf(
    "What does my birth chart reveal about my personality?",
    "How will Mercury retrograde affect me?",
    "What are my dominant signs?",
    "When is my next lucky period?"
)

// ─── Screen ───────────────────────────────────────────────────────────────────

@Composable
fun AIChatScreen(
    credits: Int = 50,
    onMenuClick: () -> Unit = {},
    onNewChatClick: () -> Unit = {}
) {
    val mediumFont   = FontFamily(Font(R.font.medium))
    val semiBoldFont = FontFamily(Font(R.font.semi_bold))
    val mediumChatFont = FontFamily(Font(R.font.inter_regular))

    var inputText by remember { mutableStateOf("") }
    val messages  = remember {
        mutableStateListOf(
            ChatMessage(
                id = "1",
                text = "Hi there. I'm synced with your birth chart and the precise positions of the planets, including past, current, and upcoming transits. Ask me anything...",
                isUser = false,
                timestamp = "12:47 PM"
            )
        )
    }

    val listState    = rememberLazyListState()
    val coroutine    = rememberCoroutineScope()

    fun sendMessage(text: String) {
        if (text.isBlank()) return
        messages.add(ChatMessage(id = System.currentTimeMillis().toString(), text = text, isUser = true))
        inputText = ""
        coroutine.launch {
            listState.animateScrollToItem(messages.size - 1)
        }
        // TODO: hook up your AI response here
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // ── Starfield background ──────────────────────────────────────────────
        Image(
            painter = painterResource(id = R.drawable.moon_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Dark overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BgDark.copy(alpha = 0.75f))
        )

        Column(modifier = Modifier.fillMaxSize().padding(vertical = 20.dp)) {

            // ── Top bar ───────────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left: hamburger + title close together
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)  // ← gap between icon and text
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hamburger_icon),
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { onMenuClick() }
                    )

                    Text(
                        text = "C L O T H O",
                        fontSize = 18.sp,
                        fontFamily = semiBoldFont,
                        color = TextWhite,
                        letterSpacing = 4.sp
                    )
                }

                // Right: new chat icon
                Image(
                    painter = painterResource(id = R.drawable.chat_icon),
                    contentDescription = "New Chat",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onNewChatClick() }
                )
            }

            // ── Messages list ─────────────────────────────────────────────────
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(messages) { message ->
                    if (message.isUser) {
                        UserMessageBubble(message = message, font = mediumFont)
                    } else {
                        AiMessageBubble(message = message, credits = credits, font = mediumChatFont)
                    }
                }
            }

            // ── Suggested prompts horizontal scroll ───────────────────────────
            if (messages.size <= 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    suggestedPrompts.forEach { prompt ->
                        SuggestedPromptChip(
                            text = prompt,
                            font = mediumChatFont,
                            onClick = { sendMessage(prompt) }
                        )
                    }
                }
            }

            // ── Divider ───────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BubbleBorder)
            )

            // ── Input bar ─────────────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BgDark)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // Text input
                BasicTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    textStyle = TextStyle(
                        color = TextWhite,
                        fontSize = 15.sp,
                        fontFamily = mediumFont
                    ),
                    cursorBrush = SolidColor(TextWhite),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    decorationBox = { innerTextField ->
                        if (inputText.isEmpty()) {
                            Text(
                                text = "Ask Anything...",
                                color = TextGrey,
                                fontSize = 15.sp,
                                fontFamily = mediumFont
                            )
                        }
                        innerTextField()
                    }
                )

                // Bottom row: + button and send button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // + attach button
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SendBtnColor)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Attach",
                            tint = TextWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Send button (up arrow)

                    Icon(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = "Send",
                        tint = if (inputText.isNotBlank()) BgDark else TextGrey,
                        modifier = Modifier.size(30.dp)
                    )

                }
            }
        }
    }
}

// ─── AI message bubble ────────────────────────────────────────────────────────

@Composable
private fun AiMessageBubble(
    message: ChatMessage,
    credits: Int,
    font: FontFamily
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Message text — no bubble, just text (matches screenshot)
            Text(
                text = message.text,
                color = TextWhite,
                fontSize = 16.sp,
                fontFamily = font,
                lineHeight = 24.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            )

            // Credits badge
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(CreditsBg)
                    .border(1.dp, BubbleBorder, CircleShape)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "$credits",
                    color = TextWhite,
                    fontSize = 13.sp,
                    fontFamily = font
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Action icons row (copy, speaker, share, info)
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("⧉", "♪", "⬆", "ℹ").forEach { icon ->
                Text(
                    text = icon,
                    color = TextGrey,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { }
                )
            }
        }

        if (message.timestamp.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = message.timestamp,
                color = TextGrey,
                fontSize = 11.sp,
                fontFamily = font
            )
        }
    }
}

// ─── User message bubble ──────────────────────────────────────────────────────

@Composable
private fun UserMessageBubble(
    message: ChatMessage,
    font: FontFamily
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp, 4.dp, 18.dp, 18.dp))
                .background(Color(0xFF1A1A2E))
                .border(1.dp, BubbleBorder, RoundedCornerShape(18.dp, 4.dp, 18.dp, 18.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                color = TextWhite,
                fontSize = 15.sp,
                fontFamily = font,
                lineHeight = 22.sp
            )
        }
    }
}

// ─── Suggested prompt chip ────────────────────────────────────────────────────

@Composable
private fun SuggestedPromptChip(
    text: String,
    font: FontFamily,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ChipBg)
            .border(1.dp, ChipBorder, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 14.dp)
    ) {
        Text(
            text = text,
            color = TextWhite,
            fontSize = 13.sp,
            fontFamily = font,
            lineHeight = 19.sp,
            textAlign = TextAlign.Start
        )
    }
}

// ─── Preview ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AIChatScreenPreview() {
    ClothoTheme {
        AIChatScreen(credits = 50)
    }
}