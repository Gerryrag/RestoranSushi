package com.example.restoran.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restoran.model.menuList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("resto_prefs", Context.MODE_PRIVATE)
    val isDark = prefs.getBoolean("is_dark_mode", false)

    Scaffold(
        containerColor = if (isDark) Color(0xFF121212) else Color(0xFFFAF3E0),
        topBar = {
            TopAppBar(
                title = { Text("Menu Sushiin", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Ornamen Jepang Background
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Pola Seigaiha (Gelombang) tipis di background
                val waveSize = 40.dp.toPx()
                val rows = (size.height / (waveSize / 3)).toInt() + 1
                val cols = (size.width / waveSize).toInt() + 2
                for (r in 0..rows) {
                    for (c in 0..cols) {
                        val xOffset = if (r % 2 == 0) 0f else waveSize / 2
                        drawCircle(
                            color = Color(0xFFD32F2F),
                            radius = waveSize / 1.5f,
                            center = Offset(c * waveSize + xOffset - (waveSize/2), r * waveSize / 3),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 0.5.dp.toPx()),
                            alpha = 0.05f
                        )
                    }
                }

                // Garis vertikal Shoji
                val shojiSpacing = 60.dp.toPx()
                for (i in 0..(size.width / shojiSpacing).toInt()) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(i * shojiSpacing, 0f),
                        end = Offset(i * shojiSpacing, size.height),
                        strokeWidth = 0.5.dp.toPx(),
                        alpha = 0.05f
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(menuList) { index, item ->
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(index * 100L)
                        visible = true
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = slideInVertically(initialOffsetY = { 50 }) + fadeIn()
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate("detail/${item.id}") },
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp, 
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isDark) Color(0xFF1E1E1E) else Color.White.copy(alpha = 0.9f)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Ikon dengan latar belakang lingkaran merah samar (Hinomaru kecil)
                                Box(contentAlignment = Alignment.Center) {
                                    Surface(
                                        shape = androidx.compose.foundation.shape.CircleShape,
                                        color = Color.Red.copy(alpha = 0.1f),
                                        modifier = Modifier.size(56.dp)
                                    ) {}
                                    Icon(
                                        item.icon, 
                                        contentDescription = null, 
                                        modifier = Modifier.size(32.dp), 
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                                
                                Spacer(modifier = Modifier.width(16.dp))
                                
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        item.nama, 
                                        fontWeight = FontWeight.ExtraBold, 
                                        fontSize = 18.sp,
                                        color = if (isDark) Color.White else Color(0xFF2D2D2D)
                                    )
                                    Text(
                                        item.harga, 
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        item.deskripsi, 
                                        fontSize = 12.sp, 
                                        maxLines = 2,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        lineHeight = 16.sp
                                    )
                                }
                                
                                // Kanji dekoratif kecil di setiap kartu
                                Text(
                                    "品", // "Hin" (Item/Product)
                                    modifier = Modifier.alpha(0.1f).padding(start = 8.dp),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
