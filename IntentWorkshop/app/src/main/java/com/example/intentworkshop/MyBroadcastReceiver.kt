package com.example.intentworkshop

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        android.util.Log.d("MyBroadcastReceiver", "onReceive triggered")
        Toast.makeText(context, "Broadcast received!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ACTION_MY_BROADCAST = "com.example.MY_ACTION"
    }
}