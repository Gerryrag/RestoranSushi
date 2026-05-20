package com.example.restoran.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restoran.R
import com.example.restoran.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onDarkModeToggle: (Boolean) -> Unit) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("resto_prefs", Context.MODE_PRIVATE)
    
    val restaurantName = remember { mutableStateOf(prefs.getString("nama_restoran", "Sushiin")) }
    val isDark = remember { mutableStateOf(prefs.getBoolean("is_dark_mode", false)) }

    Scaffold(
        containerColor = if (isDark.value) Color(0xFF121212) else Color(0xFFFAF3E0), // Authentic paper color
        topBar = {
            TopAppBar(
                title = { Text("Sushiin", fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { 
                        isDark.value = !isDark.value
                        onDarkModeToggle(isDark.value)
                    }) {
                        Icon(
                            if (isDark.value) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle Dark Mode"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Ornamen Jepang: Hinomaru dan Pola Dekoratif
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Hinomaru (Lingkaran Merah) - Dibuat lebih besar dan artistik
                drawCircle(
                    color = Color(0xFFD32F2F),
                    radius = size.minDimension / 1.8f,
                    center = Offset(size.width * 0.95f, size.height * 0.1f),
                    alpha = 0.2f
                )

                // Pola Seigaiha (Gelombang) di bagian paling bawah
                val waveSize = 80.dp.toPx()
                val rows = 4
                val cols = (size.width / waveSize).toInt() + 2
                for (r in 0..rows) {
                    for (c in 0..cols) {
                        val xOffset = if (r % 2 == 0) 0f else waveSize / 2
                        drawCircle(
                            color = Color(0xFFD32F2F),
                            radius = waveSize / 1.2f,
                            center = Offset(c * waveSize + xOffset - (waveSize/2), size.height - (r * waveSize / 4)),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
                            alpha = 0.08f
                        )
                    }
                }
                
                // Aksen garis-garis vertikal tipis (seperti tekstur pintu geser Shoji)
                val shojiSpacing = 40.dp.toPx()
                for (i in 0..(size.width / shojiSpacing).toInt()) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(i * shojiSpacing, 0f),
                        end = Offset(i * shojiSpacing, size.height),
                        strokeWidth = 0.5.dp.toPx(),
                        alpha = 0.1f
                    )
                }
            }

            // Tulisan Kanji Dekoratif "Sushi" (Besar di background)
            Text(
                "寿司", 
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 40.dp, start = 20.dp)
                    .alpha(0.1f),
                fontSize = 150.sp,
                fontWeight = FontWeight.Black,
                color = if (isDark.value) Color.White else Color.Black
            )

            // Aksen Noren (Tirai Jepang) di bagian atas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(top = padding.calculateTopPadding()),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .width(60.dp)
                            .fillMaxHeight(),
                        color = Color(0xFFD32F2F).copy(alpha = 0.15f),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD32F2F).copy(alpha = 0.3f))
                    ) {}
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { visible = true }

                AnimatedVisibility(
                    visible = visible,
                    enter = scaleIn(initialScale = 0.5f) + fadeIn()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        // Lingkaran di belakang logo
                        Surface(
                            shape = androidx.compose.foundation.shape.CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            modifier = Modifier.size(180.dp)
                        ) {}
                        
                        Image(
                            painter = painterResource(id = R.drawable.ic_sushi_logo),
                            contentDescription = "Sushiin Logo",
                            modifier = Modifier.size(150.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                
                // Label "Selamat datang" dengan style lebih rapi
                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        "スシイン - SELAMAT DATANG", 
                        fontSize = 12.sp, 
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                }

                Text(
                    restaurantName.value ?: "Sushiin", 
                    fontSize = 42.sp, 
                    fontWeight = FontWeight.ExtraBold, 
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(64.dp))
                
                Button(
                    onClick = { navController.navigate(Screen.Menu.route) },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ) {
                    Text("LIHAT MENU", fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedButton(
                    onClick = { navController.navigate(Screen.Profile.route) },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ) {
                    Text("PROFIL RESTORAN", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
