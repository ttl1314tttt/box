package com.example.box_app

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.box_app.mqtt.MainViewModel
import java.net.URL

class WbeViewActivity : AppCompatActivity() {

    val model = MainViewModel.getModelInstance()
    val operate = model.operate.value?.operationType

    lateinit var webView: WebView
    var url: String? = null
    var type: String = ""
    var mClient  = WebViewClient()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wbe_view)
        //隐藏顶部状态栏
        supportActionBar?.hide()

        //加载界面传过来的资源
        url = intent.getStringExtra("url").toString()
        //type = intent.getStringExtra("type").toString()
        Log.v("url", url!!)
        webView = findViewById<WebView?>(R.id.webView).apply {
            // 允许 JavaScript 执行
            settings.javaScriptEnabled = true
            // 允许 HTML 5 视频自动播放（默认为 false）
            settings.mediaPlaybackRequiresUserGesture = false
            // 手动调用 requestFocus() 获取焦点
            requestFocus()
        }
        webView.loadUrl(url!!)

        //观察是否需要跟换加载新资源
        model.resource.observe(this, Observer {
            val urlType = model.resource.value?.resourceType
            var filePath = model.resource.value?.filePath
            url = urlType?.let { type -> filePath?.let { path -> getUrl(type, path) } }
            url?.let { it1 -> Log.v("url", it1) }
            if ( url != null ){
                webView.loadUrl(url!!)
            }
        })

        //在当前资源内的交互操作
        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                // 对应 HTML5 标准，当页面内容加载完毕时需要等待一段时间（比如100ms）再尝试播放视频
//                view?.postDelayed({
//                    view.evaluateJavascript("document.querySelector('.-bwp-internal-container').click()", null)
//                }, 3000)
                view?.postDelayed({
                    view.evaluateJavascript("document.getElementsById('bottom').click()",null)
                },3000)

            }
        }

    }
}