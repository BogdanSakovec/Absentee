package com.example.absentee

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TodayScreen(onClick: () -> Unit) {
     Text(text = "TodayScreen", fontSize = 30.sp,
         modifier = Modifier.padding(60.dp))
        Button(onClick = { onClick() },
            modifier = Modifier.padding(100.dp))
        { Text(text = "WeekScreen") }
}
@Composable
fun WeekScreen(onClick: () -> Unit) {
     Text(text = "WeekScreen", fontSize = 30.sp,
         modifier = Modifier.padding(60.dp))
        Button(onClick = { onClick() },
            modifier = Modifier.padding(100.dp))
        { Text(text = "CityScreen") }
}
@Composable
fun CityScreen(onClick: () -> Unit) {
     Text(text = "CityScreen", fontSize = 30.sp,
         modifier = Modifier.padding(60.dp))
        Button(onClick = { onClick() },
            modifier = Modifier.padding(100.dp))
        { Text(text = "TodayScreen") }
}
