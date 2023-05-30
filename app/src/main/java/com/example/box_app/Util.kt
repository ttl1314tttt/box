package com.example.box_app

fun getUrl(type:Int, url:String) :String{
    if (type != 3){
        return "http://eh.cqxyy.net$url"
    }
    return url
}