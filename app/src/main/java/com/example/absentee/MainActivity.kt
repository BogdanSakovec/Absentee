package com.example.absentee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

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
                    Icon(painter = painterResource(id = navItem.image),
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
            title = "сьогодні",
            image = R.drawable.baseline_today_24,
            route = "today"),
        BarItem(
            title = "тиждень",
            image = R.drawable.baseline_ballot_24,
            route = "week"
        ),
        BarItem(
            title = "міста",
            image = R.drawable.baseline_wrong_location_24,
            route = "city"
        )
    )
}

data class BarItem(
    val title: String,
    val image: Int,
    val route: String)

@Composable
fun Today() {
    Text(text = "Сьогодні", fontSize = 30.sp,)
}
@Composable
fun Week() {
    Text(text = "Тиждень", fontSize = 30.sp,)
}
@Composable
fun City() {
    Text(text = "Міста", fontSize = 30.sp,)
}



sealed class NavRoutes(val route: String) {
    object TodayScreen: NavRoutes("today")
    object WeekScreen: NavRoutes("week")
    object CityScreen: NavRoutes("city")
}