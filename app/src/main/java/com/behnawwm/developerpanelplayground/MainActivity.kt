package com.behnawwm.developerpanelplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.behnawwm.developerpanelplayground.ui.theme.DeveloperPanelPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var developerPanelNotificationBuilder: DeveloperPanelNotificationBuilder

    @Inject
    lateinit var developerPanelFloatingButtonBuilder: DeveloperPanelFloatingButtonBuilder

    @Inject
    lateinit var developerPanelDrawerBuilder: DeveloperPanelDrawerBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotification()

        setContent {
            DeveloperPanelPlaygroundTheme {
                MainActivityContent()
            }
        }
    }

    @Composable
    private fun MainActivityContent() {
        developerPanelDrawerBuilder.ContainerContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = buildAnnotatedString {
                        append("Main Activity of ")
                        withStyle(style = SpanStyle(color = if (BuildConfig.DEBUG) Color.Red else Color.Green)) {
                            append(BuildConfig.BUILD_TYPE)
                        }
                    },
                    modifier = Modifier.align(Alignment.Center)
                )
                developerPanelFloatingButtonBuilder.Content()
            }
        }
    }

    private fun createNotification() {
        developerPanelNotificationBuilder.createNotification(activity = this@MainActivity)
    }
}