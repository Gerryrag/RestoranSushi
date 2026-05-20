package com.example.restoran.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: Int,
    val nama: String,
    val harga: String,
    val deskripsi: String,
    val icon: ImageVector,
    var rating: Float = 0f
)

val menuList = listOf(
    MenuItem(1, "Salmon Nigiri", "Rp 35.000", "Irisan salmon segar di atas nasi cuka premium", Icons.Default.SetMeal),
    MenuItem(2, "Maguro Sushi", "Rp 40.000", "Sushi dengan topping tuna segar berkualitas tinggi", Icons.Default.SetMeal),
    MenuItem(3, "California Roll", "Rp 45.000", "Isian kepiting, alpukat, dan timun dengan taburan tobiko", Icons.Default.SetMeal),
    MenuItem(4, "Dragon Roll", "Rp 65.000", "Unagi dan timun di dalam, topping alpukat di luar", Icons.Default.DinnerDining),
    MenuItem(5, "Salmon Sashimi", "Rp 55.000", "5 irisan tebal salmon segar kualitas sashimi", Icons.Default.SetMeal),
    MenuItem(6, "Ebi Tempura Roll", "Rp 48.000", "Sushi roll dengan isian udang tempura yang renyah", Icons.Default.DinnerDining)
)
