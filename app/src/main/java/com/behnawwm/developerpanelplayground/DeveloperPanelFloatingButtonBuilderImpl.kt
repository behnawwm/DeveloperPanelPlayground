package com.behnawwm.developerpanelplayground

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class DeveloperPanelFloatingButtonBuilderImpl : DeveloperPanelFloatingButtonBuilder {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        Box {
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            offsetX = (offsetX + dragAmount.x)
                            offsetY = (offsetY + dragAmount.y)
                        }
                    }
                    .shadow(8.dp)
                    .background(Color.White)
                    .align(Alignment.TopCenter)
                    .clickable {
                        context.startActivity(
                            Intent(context, DeveloperPanelActivity::class.java)
                        )
                    },
                tint = Color.Magenta,
            )
        }
    }
}