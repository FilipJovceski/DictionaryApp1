package com.example.dictionaryapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryapp.data.sampleWords
import com.example.dictionaryapp.model.Word
import com.example.dictionaryapp.ui.components.WordCard

@Composable
fun HomeScreen(onWordClick: (Word) -> Unit) {

    // State за пребарување и филтер
    var searchQuery    by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Сите") }

    val categories = listOf("Сите", "Именка", "Глагол", "Придавка")

    // Филтрирање на зборовите
    val filteredWords = sampleWords.filter { word ->
        val matchesSearch = word.term.contains(searchQuery, ignoreCase = true) ||
                word.definition.contains(searchQuery, ignoreCase = true)
        val matchesFilter = selectedFilter == "Сите" || word.category == selectedFilter
        matchesSearch && matchesFilter
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ── Наслов ──────────────────────────────────────────
        Text(
            text = "📖 Речник",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "${sampleWords.size} зборови достапни",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── Search Bar ──────────────────────────────────────
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Пребарај збор...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Пребарај"
                )
            },
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ── Filter Chips ────────────────────────────────────
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { category ->
                FilterChip(
                    selected = selectedFilter == category,
                    onClick  = { selectedFilter = category },
                    label    = { Text(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ── Број на резултати ───────────────────────────────
        Text(
            text = "Прикажани: ${filteredWords.size} зборови",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ── Листа со зборови ────────────────────────────────
        if (filteredWords.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("😕", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Нема резултати за \"$searchQuery\"",
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(filteredWords, key = { it.id }) { word ->
                    WordCard(word = word, onClick = { onWordClick(word) })
                }
            }
        }
    }
}