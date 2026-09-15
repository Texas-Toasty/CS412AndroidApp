package com.example.intentworkshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intentworkshop.SecondActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityScreen(
                onExplicitClick = {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                },

                onImplicitClick = {
                    val intent = Intent("com.example.assignment2.SHOW_SECOND_ACTIVITY")
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun MainActivityScreen(
    onExplicitClick: () -> Unit,
    onImplicitClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Reece Parry")

        Text("Student ID: 1550752")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onExplicitClick
        ) {
            Text("Start Activity Explicitly")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onImplicitClick
        ) {
            Text("Start Activity Implicitly")
        }
    }
}