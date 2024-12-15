package com.example.absentee.BottNav

sealed class BottomItem(val title: String, val route: String) {
    object TodayScreen: BottomItem("сьогодні", "Today")
    object WeekScreen: BottomItem("тиждень", "Weekday")
    object CityScreen: BottomItem("міста", "Cityday")
}