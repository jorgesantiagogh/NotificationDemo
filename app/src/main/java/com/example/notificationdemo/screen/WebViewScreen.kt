package com.example.notificationdemo.screen

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController

@Composable
fun WebViewScreen(url: String) {
    val navController = rememberNavController()
    navController.enableOnBackPressed(true)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Deep Linked WebView, WOW!") },
                navigationIcon =
                {
                    var backPressedCount by remember { mutableStateOf(0) }
                    BackHandler { backPressedCount++ }

                    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

            )
        },
        content = {
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            }, update = {
                it.loadUrl(url)
            })
        }
    )
}
