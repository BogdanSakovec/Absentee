package com.example.absentee

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.absentee.ui.theme.AbsenteeTheme
import androidx.compose.material3.TopAppBar as TopAppBar1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

@Composable
fun Main(){
    val navController = rememberNavController()
    Column(Modifier.padding(8.dp)) {
        NavHost(navController,
            startDestination = NavRoutes.TodayScreen.route,
            modifier = Modifier.weight(1f) ) {

            composable(NavRoutes.TodayScreen.route) { Today() }
            composable(NavRoutes.WeekScreen.route) { Week() }
            composable(NavRoutes.CityScreen.route) { City() }
        }
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        {saveState = true}
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Today",
            image = Icons.Filled.Warning,
            route = "today"),
        BarItem(
            title = "Week",
            image = Icons.Filled.Info,
            route = "week"
        ),
        BarItem(
            title = "City",
            image = Icons.Filled.LocationOn,
            route = "city"
        )
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String)

@Composable
fun Today() {
    Text(text = "Today Screen", fontSize = 30.sp,)
}
@Composable
fun Week() {
    Text(text = "Week Screen", fontSize = 30.sp,)
}
@Composable
fun City() {
    Text(text = "City Screen", fontSize = 30.sp,)
}

sealed class NavRoutes(val route: String) {
    object TodayScreen: NavRoutes("today")
    object WeekScreen: NavRoutes("week")
    object CityScreen: NavRoutes("city")
}