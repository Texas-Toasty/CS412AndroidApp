# Assignment 6 - UI Automation Testing

## Device/Emulator

Pixel 8 Emulator

## Android OS Version

API 37.1

## Description

This application demonstrates Android activity lifecycle management, explicit and
implicit intents, a foreground service, a dynamically registered broadcast receiver,
and capturing an image with the device camera through an intent.

## Main Activity

The Main Activity:

- Displays the student's full name and student ID
- Launches the Second Activity using an explicit intent
- Launches the Second Activity using an implicit intent
- Starts MyService as a foreground service (Start Service button)
- Binds to MyService and displays the grade returned by getMyGrade() beside the
  Bind Service button
- Sends a custom broadcast with action "com.example.MY_ACTION" (Send Broadcast button)
- Dynamically registers MyBroadcastReceiver in onStart() and unregisters it in onStop()
- Launches the Third Activity (View Image Activity button)

## Second Activity

The Second Activity:

- Displays five mobile software engineering challenges
- Includes a button to return to the Main Activity

## Third Activity

The Third Activity:

- Has a "Capture Image" button at the top of the screen
- Opens the device's camera app using an intent (MediaStore.ACTION_IMAGE_CAPTURE,
  through the TakePicturePreview activity result contract)
- Displays the captured image below the button
- Needs no CAMERA permission, because the picture is taken by the camera app rather
  than by this app

## UI Automator Test

UiAutomatorTest (app/src/androidTest) automatically:

Presses Home and launches the app by clicking its icon in the launcher
Clicks the "Start Activity Explicitly" button in the Main Activity
Verifies the Second Activity displays at least one of the mobile software engineering challenges

Run it from Android Studio by clicking the green arrow next to UiAutomatorTest on an emulator or device (the screen must be unlocked).

## MyService

- A foreground service
- When started, shows a notification: "The service has started"
- When bound, exposes getMyGrade(), which MainActivity calls and displays on screen

## MyBroadcastReceiver

- Registered dynamically (not in the manifest) using an IntentFilter for the
  "com.example.MY_ACTION" action
- Displays a Toast message "Broadcast received!" when the broadcast arrives
