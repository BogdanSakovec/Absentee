package com.example.absentee

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

const val API_KEY = "4d55df4378394db1a0a142718251201"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
            Greeting("Lutsk", this)
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    Column(Modifier.padding(8.dp)) {
        NavBar(navController = navController)
        NavHost(navController, startDestination = NavRoutes.Weather.route) {
            composable(NavRoutes.Weather.route) { Weather() }
            composable(NavRoutes.Add.route) { Add()  }
            composable(NavRoutes.Town.route) { Town() }
        }
    }
}

@Composable
fun NavBar(navController: NavController){
    Spacer(modifier = Modifier.height(40.dp))
    Column {
        Row(
            Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Box(modifier = Modifier.background(Color.Blue).height(30.dp).width(100.dp)){
                Text("ПОГОДА",
                    Modifier.clickable { navController.navigate(NavRoutes.Weather.route) },
                    fontSize = 22.sp, color= Color.White)
            }
            Box(modifier = Modifier.background(Color.Blue).height(30.dp).width(100.dp)){
                Text("ДОДАТИ",
                    Modifier.clickable { navController.navigate(NavRoutes.Add.route) },
                    fontSize = 22.sp, color= Color.White)
            }
            Box(modifier = Modifier.background(Color.Blue).height(30.dp).width(100.dp)){
                Text("МІСТА",
                    Modifier.clickable { navController.navigate(NavRoutes.Town.route) },
                    fontSize = 22.sp, color= Color.White)
            }
        }
    }
}

@Composable
fun Weather(){
    Column {
        Text("ПОГОДА", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun Greeting(name: String, context: Context) {
    val state = remember {
        mutableStateOf("Unknown")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Box(
            modifier = Modifier
                .fillMaxHeight(0.1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Температура повітря в $name: ${state.value} Cº",
                fontSize = 30.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(0.1f)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
                getData(name, context, state)
            }, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                Text(text = "Взнати погоду")
            }
        }
    }
}

fun getData(name: String, context: Context, mState: MutableState<String>){
    val url = "https://api.weatherapi.com/v1/current.json" +
            "?key=$API_KEY&" +
            "q=$name" +
            "&aqi=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response->
            val obj = JSONObject(response)
            val temp = obj.getJSONObject("current")
            mState.value = temp.getString("temp_c")
            Log.d("MyLog","Response: ${temp.getString("temp_c")}")
        },
        {
            Log.d("MyLog","Volley error: $it")
        }
    )
    queue.add(stringRequest)
}

@Composable
fun Add(vm: UserListViewModel = viewModel()){
    Column {
        Spacer(modifier = Modifier.height(200.dp))
        UserData(vm.userName, changeName = {vm.changeName(it)}, add={vm.addUser()})
        UserList(users = vm.users, delete = {vm.deleteUser(it)})
    }

    Text("ДОДАТИ", fontSize = 30.sp)
}

@Composable
fun Town(vm: UserListViewModel = viewModel()){
    Column {
        Spacer(modifier = Modifier.height(200.dp))
        UserList(users = vm.users, delete = {vm.deleteUser(it)})
    }
    Text("МІСТА", fontSize = 30.sp)
}

data class User(val name: String)
class UserListViewModel: ViewModel() {

    val users = mutableStateListOf<User>(User("Lutsk"),
        User("Lviv"))
    var userName by mutableStateOf("")
    fun addUser(){
        users.add(User(userName)) }
    fun changeName(value: String){
        userName = value }
    fun deleteUser(user: User){
        users.remove(user)
    }
}

@Composable
fun UserList(users:List<User>, delete:(User)->Unit) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(users) {u ->
            Text(u.name, Modifier.padding(start=12.dp), fontSize = 25.sp)
            TextButton(onClick = {delete(u)}) {
                Text("Delete", fontSize = 22.sp)
            }
        }
    }
}

@Composable
fun UserData(userName: String, changeName:(String)->Unit, add:()->Unit){
    Column{
        OutlinedTextField(value = userName, modifier=Modifier.padding(8.dp),
            label = {Text("Введіть назву міста")}, onValueChange = {changeName(it)})
        Button(onClick = { add() }, Modifier.padding(8.dp)) {
            Text("Додати", fontSize = 22.sp)
        }
    }
}

sealed class NavRoutes(val route: String) {
    object Weather : NavRoutes("weather")
    object Add : NavRoutes("add")
    object Town : NavRoutes("town")
}