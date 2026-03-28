package com.example.myfirstapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.model.Chat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    chats: List<Chat>,
    onChatClick: (Int) -> Unit,
) {
    var showModelSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.arconsisbg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "BastiGPT",
                            color = Color.Black
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White.copy(alpha = 0.8f),
                        scrolledContainerColor = Color.White.copy(alpha = 0.8f)
                    )
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    NewChatListItem(
                        onClick = { showModelSheet = true }
                    )
                }

                item {
                    Text(
                        text = "Recent",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                if (chats.isEmpty()) {
                    item {
                        Text(
                            text = "No chats yet",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                } else {
                    items(chats) { chat ->
                        ChatListItem(
                            title = chat.name,
                            onClick = { onChatClick(chat.id) }
                        )
                    }
                }
            }
        }

        if (showModelSheet) {
            ModelActionSheet(
                sheetState = sheetState,
                onDismiss = { showModelSheet = false }
            )
        }
    }
}

@Composable
private fun StyledChatCard(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(22.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.8f)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.35f),
                    shape = shape
                )
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun ChatListItem(
    title: String,
    onClick: () -> Unit
) {
    StyledChatCard(onClick = onClick) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
    }
}

@Composable
private fun NewChatListItem(
    onClick: () -> Unit
) {
    StyledChatCard(onClick = onClick) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "New Chat",
                tint = Color.Black
            )
            Text(
                text = "New Chat",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModelActionSheet(
    sheetState: androidx.compose.material3.SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White.copy(alpha = 0.96f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = "Choose a model",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            ModelSheetItem(
                title = "Llama 3.1",
                subtitle = "Balanced general assistant",
                onClick = onDismiss
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            )

            ModelSheetItem(
                title = "Mistral",
                subtitle = "Fast and lightweight",
                onClick = onDismiss
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            )

            ModelSheetItem(
                title = "Gemma 2",
                subtitle = "Strong instruction following",
                onClick = onDismiss
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            )

            ModelSheetItem(
                title = "Phi-3",
                subtitle = "Compact and efficient",
                onClick = onDismiss
            )
        }
    }
}

@Composable
private fun ModelSheetItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 18.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}