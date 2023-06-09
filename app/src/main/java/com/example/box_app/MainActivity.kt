package com.example.box_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.box_app.mqtt.MainViewModel
import com.example.box_app.mqtt.MyMqtt

class MainActivity : AppCompatActivity() {

    val model = MainViewModel.getModelInstance()
    var url:String? = null

    val mqtt = MyMqtt(this)
    var value:String = ""
    val videoUrl:String = "https://www.bilibili.com/video/BV1k24y1P7pL/?spm_id_from=333.1007.tianma.3-3-9.click&vd_source=f4f966b7dbe32cef48dbb9756339fac4"
    var pdfUrl:String = "http://ow365.cn/owview/p/pv.aspx?PowerPointView=ReadingView&WOPISrc=http%3A%2F%2Foosh%2Fwopi%2Ffiles%2F%40%2Fwopi%3FvId%3DO0SQ5A48FzwAri4BY9X0RA--&bs=c291cmNlLmpscmMuY29tLmNuLjgwXGd4YnlzMTAwLnBwdHg-&token=RW8a2IMpC925KNEv8zsmrmDtRdSg97RN&cancopy="
    var http:String = "http://files/2023/05/29/39503733986729984.pdf"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //隐藏顶部状态栏
        supportActionBar?.hide()

        //初始化mqtt通讯
        mqtt.initMqtt()
        model.resource.observe(this, Observer {
            val urlType = model.resource.value?.resourceType
            var filePath = model.resource.value?.filePath
            url = urlType?.let { type -> filePath?.let { path -> getUrl(type, path) } }
            url?.let { it1 -> Log.v("url", it1) }
            if ( url != null ){
                var intent = Intent()
                intent.setClass(this,WbeViewActivity::class.java)
                intent.putExtra("url",url)
                this.startActivity(intent)
            }
        })
    }


}

