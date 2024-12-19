package com.example.absentee.BottomNavigaton

data class BarItem(
    val title: String,
    val route: String)
object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "сьогодні",
            route = "TodayScreen"),
        BarItem(
            title = "тиждень",
            route = "WeekScreen"
        ),
        BarItem(
            title = "міста",
            route = "CityScreen"
        )
    )
}

sealed class NavRoutes(val route: String) {
    object TodayScreen: NavRoutes("сьогодні")
    object WeekScreen: NavRoutes("тиждень")
    object CityScreen: NavRoutes("міста")
}