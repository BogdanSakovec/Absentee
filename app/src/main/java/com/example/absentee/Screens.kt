package com.example.absentee

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodayScreen(context: Context,onClick: () -> Unit) {
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Back",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(titleContentColor = Color.DarkGray,
                    containerColor =Color.Red),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Forward")
                },

                onClick = {
                    onClick()
                }
            )
        },
    ) {
        Text(
            text = "TodayScreen",
            fontSize = 30.sp,
            modifier = Modifier.padding(it)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeekScreen(context: Context,onClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Back",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Forward")
                },

                onClick = {
                    onClick()
                }
            )
        },

    ) {
        Text(
            text = "WeekScreen",
            fontSize = 30.sp,
            modifier = Modifier.padding(it)
        )
        Spacer(modifier = Modifier.height(30.dp).background(color = Color.Red))
        Button(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(text = "TodayScreen",
                color = Color.White
                )
        }
    }
}
