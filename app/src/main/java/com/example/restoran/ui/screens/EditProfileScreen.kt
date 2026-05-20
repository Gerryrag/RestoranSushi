package com.example.restoran.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("resto_prefs", Context.MODE_PRIVATE)

    var nama by rememberSaveable { mutableStateOf(prefs.getString("nama_restoran", "Sushiin") ?: "") }
    var alamat by rememberSaveable { mutableStateOf(prefs.getString("alamat", "Jl. Raya Malang No. 12, Jawa Timur") ?: "") }
    var deskripsi by rememberSaveable { mutableStateOf(prefs.getString("deskripsi", "Restoran masakan Nusantara autentik sejak 1995") ?: "") }
    var jamBuka by rememberSaveable { mutableStateOf(prefs.getString("jam_buka", "08.00 - 22.00 WIB") ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit Profil") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(value = nama, onValueChange = { nama = it }, label = { Text("Nama Restoran") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = alamat, onValueChange = { alamat = it }, label = { Text("Alamat") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = deskripsi, onValueChange = { deskripsi = it }, label = { Text("Deskripsi") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
            OutlinedTextField(value = jamBuka, onValueChange = { jamBuka = it }, label = { Text("Jam Buka") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.weight(1f))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f)) {
                    Text("Batal")
                }
                Button(
                    onClick = {
                        prefs.edit().apply {
                            putString("nama_restoran", nama)
                            putString("alamat", alamat)
                            putString("deskripsi", deskripsi)
                            putString("jam_buka", jamBuka)
                        }.apply()
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}
