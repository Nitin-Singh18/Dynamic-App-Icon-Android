package com.example.dynamicappicon

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dynamicappicon.ui.theme.DynamicAppIconTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainActivityAlias = "${applicationContext.packageName}.MainActivityAlias"
            val mainActivity = "${applicationContext.packageName}.MainActivity"
            DynamicAppIconTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        changeEnabledComponent(
                            mainActivity,
                            mainActivityAlias
                        )
                    }) {
                        Text(text = "Big Icon")
                    }
                    Button(onClick = {
                        changeEnabledComponent(
                            mainActivityAlias,
                            mainActivity
                        )
                    }) {
                        Text(text = "Small Icon")
                    }
                }
            }
        }
    }
}

fun Activity.changeEnabledComponent(
    enabled: String,
    disabled: String,
) {
    packageManager.setComponentEnabledSetting(
        ComponentName(
            this,
            enabled
        ),
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )
    packageManager.setComponentEnabledSetting(
        ComponentName(
            this,
            disabled
        ),
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )
}