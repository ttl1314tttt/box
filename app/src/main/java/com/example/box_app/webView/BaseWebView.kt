package com.example.box_app.webView

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.box_app.Constant
import com.example.box_app.Constant.Companion.EMPTY

interface BaseWebView  {
    public open var webView :WebView?
    var TAG : Int

    /**
     * 上一页
     * */
    fun prePage(webView: WebView):String

    /**
     * 下一页
     * */
    fun nextPage(webView: WebView):String

    /**
     * 视频播放
     * */
    fun play(webView: WebView):String

    /**
     * 视频暂停
     * */
    fun stop(webView: WebView):String

    /**
     * 网页传参cookie
     * */
    fun cookie(webView: WebView)
}