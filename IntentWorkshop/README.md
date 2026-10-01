Assignment 3 - Services and Broadcast Receivers
Device/Emulator

Pixel 8 Emulator

Android OS Version

API 37

Description

This application demonstrates Android activity lifecycle management, explicit and implicit intents, a foreground service, and a dynamically registered broadcast receiver.

Main Activity

The Main Activity:

Displays the student's full name and student ID
Launches the Second Activity using an explicit intent
Launches the Second Activity using an implicit intent
Starts MyService as a foreground service (Start Service button)
Binds to MyService and displays the grade returned by getMyGrade() beside the Bind Service button
Sends a custom broadcast with action "com.example.MY_ACTION" (Send Broadcast button)
Dynamically registers MyBroadcastReceiver in onStart() and unregisters it in onStop()
Second Activity

The Second Activity:

Displays five mobile software engineering challenges
Includes a button to return to the Main Activity
MyService
A foreground service
When started, shows a notification: "The service has started"
When bound, exposes getMyGrade(), which MainActivity calls and displays on screen
MyBroadcastReceiver
Registered dynamically (not in the manifest) using an IntentFilter for the "com.example.MY_ACTION" action
Displays a Toast message "Broadcast received!" when the broadcast arrives
