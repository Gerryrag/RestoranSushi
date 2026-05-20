package com.example.restoran.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restoran.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("resto_prefs", Context.MODE_PRIVATE)

    // Using key(Unit) to force re-read when navigating back
    val profileData = key(Unit) {
        val nama = prefs.getString("nama_restoran", "Sushiin") ?: ""
        val alamat = prefs.getString("alamat", "Little Tokyo, Jakarta Selatan") ?: ""
        val deskripsi = prefs.getString("deskripsi", "Sushi autentik dengan bahan segar dari pasar Tsukiji") ?: ""
        val jamBuka = prefs.getString("jam_buka", "11.00 - 22.00 WIB") ?: ""
        listOf(
            Icons.Default.Store to nama,
            Icons.Default.LocationOn to alamat,
            Icons.Default.Info to deskripsi,
            Icons.Default.Schedule to jamBuka
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Restoran") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    profileData.forEach { (icon, text) ->
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text, fontSize = 16.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate(Screen.EditProfile.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Profil")
            }
        }
    }
}
