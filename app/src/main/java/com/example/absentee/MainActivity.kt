package com.example.absentee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
            Main()
        }
    }
}

@Composable
fun Main(){
    val navController = rememberNavController()
    val townes = listOf(
        Town(21001, "Vinnytsia", 661),
        Town(40000, "Sumy", 369),
        Town(36000, "Poltava", 850),
        Town(65003, "Odesa", 230),
        Town(54001, "Mykolaiv", 235),
        Town(79000, "Lviv", 768),
        Town(76002, "Ivano-Frankivsk", 362),
        Town(69001, "Zaporozhye", 952),
        Town(88000, "Uzhhorod", 1131),
        Town(10001, "Zhytomyr", 1140),
        Town(49000, "Dnipro", 248),
        Town(1001, "Kyiv", 1542),
        Town(61000, "Kharkiv", 357),
        Town(43000, "Lutsk", 939)
    )
    Column(Modifier.padding(8.dp)) {
        NavHost(navController,
            startDestination = NavRoutes.Today.route,
            modifier = Modifier.weight(1f) ) {

            composable(NavRoutes.Today.route) {}
            composable(NavRoutes.Week.route) { Week() }
            composable(NavRoutes.City.route) { City(townes, navController) }
            composable(NavRoutes.Today.route + "/{todayId}",
                arguments = listOf(navArgument("todayId") {type = NavType.IntType})) {
                stackEntry ->
                    val todayId = stackEntry.arguments?.getInt("todayId")
                    Today(todayId, townes)
            }
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
data class Town(val id:Int, val name:String, val age:Int)

@Composable
fun Today(todayId:Int?, data: List<Town>) {
    val today = data.find { it.id==todayId }
    if(today!=null) {
        Column {
            Text("Id: ${today.id}", Modifier.padding(8.dp), fontSize = 22.sp)
            Text("Name: ${today.name}", Modifier.padding(8.dp), fontSize = 22.sp)
            Text("Age: ${today.age}", Modifier.padding(8.dp), fontSize = 22.sp)
        }
    }
    else{
        Text("Town Not Found")
    }
}
@Composable
fun Week() {
    Text(text = "Тиждень", fontSize = 30.sp,)
}
@Composable
fun City(data: List<Town>, navController: NavController) {
    LazyColumn {
        items(data){
            u->
                Row(Modifier.fillMaxWidth()){
                    Text(u.name,
                        Modifier.padding(8.dp). clickable
                        { navController.navigate("today/${u.id}") },
                        fontSize = 28.sp)

                }
        }
    }
}

sealed class NavRoutes(val route: String) {
    object Today: NavRoutes("today")
    object Week: NavRoutes("week")
    object City: NavRoutes("city")
}