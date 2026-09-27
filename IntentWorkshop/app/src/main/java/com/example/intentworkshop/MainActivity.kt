package com.example.intentworkshop

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.intentworkshop.SecondActivity

class MainActivity : ComponentActivity() {

    private var myService: MyService? = null
    private var isBound = false
    private var gradeState = mutableStateOf<String?>(null)

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val myBinder = binder as MyService.MyBinder
            myService = myBinder.getService()
            isBound = true
            gradeState.value = myService?.getMyGrade()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            myService = null
            isBound = false
        }
    }

    private val myBroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val grade by gradeState

            val notificationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { }

            MainActivityScreen(
                grade = grade,
                onExplicitClick = {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                },
                onImplicitClick = {
                    val intent = Intent("com.example.intentworkshop.SHOW_SECOND_ACTIVITY")
                    startActivity(intent)
                },
                onStartServiceClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                    }
                    val intent = Intent(this, MyService::class.java)
                    ContextCompat.startForegroundService(this, intent)
                },
                onBindServiceClick = {
                    val intent = Intent(this, MyService::class.java)
                    bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
                },
                onSendBroadcastClick = {
                    val intent = Intent(MyBroadcastReceiver.ACTION_MY_BROADCAST)
                    intent.setPackage(packageName)
                    android.util.Log.d("MainActivity", "sending broadcast")
                    sendBroadcast(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(MyBroadcastReceiver.ACTION_MY_BROADCAST)
        ContextCompat.registerReceiver(
            this,
            myBroadcastReceiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
        android.util.Log.d("MainActivity", "receiver registered")
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }
}

@Composable
fun MainActivityScreen(
    grade: String?,
    onExplicitClick: () -> Unit,
    onImplicitClick: () -> Unit,
    onStartServiceClick: () -> Unit,
    onBindServiceClick: () -> Unit,
    onSendBroadcastClick: () -> Unit
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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onStartServiceClick
        ) {
            Text("Start Service")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = onBindServiceClick
            ) {
                Text("Bind Service")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = grade?.let { "Grade: $it" } ?: "")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSendBroadcastClick
        ) {
            Text("Send Broadcast")
        }
    }
}