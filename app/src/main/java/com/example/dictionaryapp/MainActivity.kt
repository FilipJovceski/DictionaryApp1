package com.example.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.example.dictionaryapp.model.Word
import com.example.dictionaryapp.ui.DetailScreen
import com.example.dictionaryapp.ui.HomeScreen
import com.example.dictionaryapp.ui.theme.DictionaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DictionaryNavigation()
                }
            }
        }
    }
}

@Composable
fun DictionaryNavigation() {
    // null = сме на HomeScreen, Word = сме на DetailScreen
    var selectedWord by remember { mutableStateOf<Word?>(null) }

    if (selectedWord == null) {
        HomeScreen(onWordClick = { word -> selectedWord = word })
    } else {
        DetailScreen(
            word = selectedWord!!,
            onBack = { selectedWord = null }
        )
    }
}