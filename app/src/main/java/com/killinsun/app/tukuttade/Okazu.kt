package com.killinsun.app.tukuttade

import android.graphics.Bitmap
import android.util.Log
import java.util.*

class Okazu(okazuName:String, ttl:Int, okazuBitmap: Bitmap?) {

    var name:String
    var ttl:Int
    var imgBm: Bitmap?
    var date: Date

    val mapExpir:Map<Int, Int> = mapOf(
        0 to 0, //index:0 今日中
        1 to 3, //index:1 三日後
        2 to 7  //index:2 一週間後
    )


    init{
        this.name = okazuName
        this.ttl  = ttl
        this.imgBm = okazuBitmap
        date = Date()

        // Set expirnation date.
        val cl:Calendar = Calendar.getInstance()
        cl.setTime(date)
        cl.add(Calendar.DAY_OF_MONTH, mapExpir.get(ttl)!!)

        date = cl.time

    }

    fun printMyStatus(){
        Log.v("test", "Name: ${this.name}, Expir: ${date}, URI: ${this.imgBm}")
    }


}