package com.example.absentee.BottomNavigaton

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String)
object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "TodayScreen",
            image = Icons.Filled.Warning,
            route = "TodayScreen"),
        BarItem(
            title = "WeekScreen",
            image = Icons.Filled.Info,
            route = "WeekScreen"
        ),
        BarItem(
            title = "CityScreen",
            image = Icons.Filled.LocationOn,
            route = "CityScreen"
        )
    )
}

sealed class NavRoutes(val route: String) {
    object TodayScreen: NavRoutes("TodayScreen")
    object WeekScreen: NavRoutes("WeekScreen")
    object CityScreen: NavRoutes("CityScreen")
}