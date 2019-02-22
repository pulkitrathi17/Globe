package com.example.globe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebViewClient
import com.example.globe.GlobeApplication
import com.example.globe.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        var url: String = intent.getStringExtra("URL")

        webview!!.settings.javaScriptEnabled = true

        webview!!.webViewClient = object  : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: android.webkit.WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        webview!!.loadUrl(url)
    }

}
