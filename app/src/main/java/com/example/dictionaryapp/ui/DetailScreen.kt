package com.example.dictionaryapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryapp.model.Word
import com.example.dictionaryapp.ui.components.categoryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    word: Word,
    onBack: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(word.isFavorite) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали за збор") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {

            // ── Збор и категорија ───────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = word.term,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Surface(
                    color = categoryColor(word.category),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = word.category,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Дефиниција ──────────────────────────────────
            InfoCard(
                emoji = "📝",
                title = "Дефиниција",
                content = word.definition
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Пример ──────────────────────────────────────
            InfoCard(
                emoji = "💬",
                title = "Пример реченица",
                content = word.example,
                isItalic = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ── Омилен копче ────────────────────────────────
            Button(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFavorite)
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (isFavorite) "❤️ Отстрани од омилени" else "🤍 Додај во омилени",
                    fontSize = 16.sp
                )
            }
        }
    }
}

// Повторлива картичка за информации
@Composable
fun InfoCard(emoji: String, title: String, content: String, isItalic: Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "$emoji  $title",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontSize = 16.sp,
                fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}