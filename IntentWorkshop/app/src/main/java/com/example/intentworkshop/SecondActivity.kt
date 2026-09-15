package com.example.intentworkshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SecondActivityScreen(
                onMainActivityClick = {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun SecondActivityScreen(
    onMainActivityClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Mobile Software Engineering Challenges")

        Text("1. Device Fragmentation - Compatibility with devices of varying screen sizes")

        Text("2. OS Fragmentation - Developing apps that work on multiple platforms")

        Text(
            "3. Unstable and Dynamic Environments - Providing access to key features and data" +
                    " even in unstable environments"
        )

        Text(
            "4. Rapid Changes - Each updated version of the platform requires significant " +
                    "development and maintenance effort"
        )

        Text("5. Tool Support - We work with suboptimal emulators and simulators")

        Button(
            onClick = onMainActivityClick,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Main Activity")
        }
    }
}