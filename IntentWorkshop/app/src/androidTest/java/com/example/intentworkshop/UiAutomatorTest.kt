package com.example.intentworkshop

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiAutomatorTest {

    private lateinit var device: UiDevice
    private val appPackage = InstrumentationRegistry.getInstrumentation().targetContext.packageName

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        val launcherPackage = device.launcherPackageName
        assertNotNull("Launcher package not found", launcherPackage)
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT)
    }

    @Test
    fun explicitButtonOpensSecondActivityWithChallenges() {

        openAppDrawer()
        val appIcon = UiSelector().text(APP_NAME)
        UiScrollable(UiSelector().scrollable(true)).scrollIntoView(appIcon)
        assertTrue("App icon not found in launcher", device.findObject(appIcon).exists())
        device.findObject(appIcon).click()
        device.wait(Until.hasObject(By.pkg(appPackage).depth(0)), TIMEOUT)


        val explicitButton = device.wait(
            Until.findObject(By.text("Start Activity Explicitly")),
            TIMEOUT
        )
        assertNotNull("Start Activity Explicitly button not found", explicitButton)
        explicitButton.click()


        val secondActivityShown = device.wait(
            Until.hasObject(By.text("Mobile Software Engineering Challenges")),
            TIMEOUT
        )
        assertTrue("Second activity did not open", secondActivityShown)

        val challenges = listOf(
            "Device Fragmentation",
            "OS Fragmentation",
            "Unstable and Dynamic Environments",
            "Rapid Changes",
            "Tool Support"
        )
        val challengeFound = challenges.any { device.hasObject(By.textContains(it)) }
        assertTrue("No challenge text found in second activity", challengeFound)
    }

    private fun openAppDrawer() {
        val width = device.displayWidth
        val height = device.displayHeight
        device.swipe(width / 2, (height * 0.9).toInt(), width / 2, (height * 0.3).toInt(), 20)
    }

    companion object {
        private const val APP_NAME = "IntentWorkshop"
        private const val TIMEOUT = 5000L
    }
}