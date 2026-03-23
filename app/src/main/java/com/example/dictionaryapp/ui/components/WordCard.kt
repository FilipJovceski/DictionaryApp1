package com.example.dictionaryapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryapp.model.Word

@Composable
fun WordCard(
    word: Word,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Лева страна — збор и дефиниција
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = word.term,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = word.definition,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Десна страна — категорија badge
            Surface(
                color = categoryColor(word.category),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = word.category,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

// Различна боја за секоја категорија
@Composable
fun categoryColor(category: String) = when (category) {
    "Именка"   -> MaterialTheme.colorScheme.primary
    "Глагол"   -> MaterialTheme.colorScheme.tertiary
    "Придавка" -> MaterialTheme.colorScheme.secondary
    else       -> MaterialTheme.colorScheme.outline
}