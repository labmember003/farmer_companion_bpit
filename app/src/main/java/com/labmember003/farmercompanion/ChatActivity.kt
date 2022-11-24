package com.labmember003.farmercompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.airbnb.lottie.LottieAnimationView
import kotlin.system.exitProcess

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val url = "https://www.ipuranklist.com/student"
        val webView = findViewById<WebView>(R.id.resultWebView)
        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.loader1)
        webView.settings.javaScriptEnabled = true
        webView.settings.userAgentString = "Android"
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                lottieAnimationView.visibility = View.GONE
                webView.visibility = View.VISIBLE
            }
        }
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.resultWebView)
        //super.onBackPressed()
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
//            isEnabled = false
            super.onBackPressed()
        }
    }

}

