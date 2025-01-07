package com.example.absentee

import android.app.ActionBar.Tab
import android.content.ClipData.Item
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main ()

        }
    }
}
@Composable
fun Main() {
    val navController = rememberNavController()
    Column(Modifier.padding(8.dp)) {
        NavHost(navController, startDestination = NavRoutes.
        Add.route, modifier = Modifier.weight(1f)) {
            composable(NavRoutes.Add.route) { Add() }
            composable(NavRoutes.Weather.route) { Weather() }
            composable(NavRoutes.City.route) { City() }
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
            NavigationBarItem(
                selected = currentRoute == navItem.route,
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
            title = "Add",
            image = Icons.Filled.Add,
            route = "Add"
        ),
        BarItem(
            title = "Weather",
            image = Icons.Filled.Star,
            route = "Weather"
        ),
        BarItem(
            title = "City",
            image = Icons.Filled.LocationOn,
            route = "City"
        ),
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

@Composable
fun Add(){
    Text("Add Page", fontSize = 30.sp)
    val message = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Text(message.value, fontSize = 28.sp)
        TextField(
            value = message.value,
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = {newText -> message.value = newText}
        )
    }
}
@Composable
fun Weather(){
    Text("Weather Page", fontSize = 30.sp)
}
@Composable
fun City(){
    val towns = listOf("Kyiv","Lviv","Odesa",
        "Kharkiv","Dnipro","Zaporizhzhia","Donetsk",
        "Luhansk","Vinnytsia","Chernivtsi","Ternopil",
        "Ivano-Frankivsk","Sumy","Poltava","Cherkasy",
        "Rivne","Khmelnytskyi","Zhytomyr","Chernihiv",
        "Uzhhorod","Lutsk","Kropyvnytskyi","Kremenchuk")
    LazyColumn(
        Modifier.fillMaxSize()
    ){
        listHeader(text = "Cities of Ukraine")
        items(towns){town -> Text(town, fontSize = 24.sp) }
    }
}
@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.listHeader(text: String) {
    stickyHeader {
        Text(
            text = text,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(all = 16.dp)
        )
    }
}

sealed class NavRoutes(val route: String) {
    object Add : NavRoutes("add")
    object Weather : NavRoutes("weather")
    object City : NavRoutes("city")
}
