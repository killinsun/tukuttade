package com.killinsun.app.tukuttade

import android.graphics.Bitmap
import android.util.Log
import java.util.*

class Okazu(okazuName:String, ttl:Int?, date: Date?, imgByte: ByteArray?) {

    var name:String
    var ttl:Int?
    var imgByte: ByteArray?
    var date: Date?

    val mapExpir:Map<Int, Int> = mapOf(
        0 to 0, //index:0 今日中
        1 to 3, //index:1 三日後
        2 to 7  //index:2 一週間後
    )


    init{
        this.name = okazuName
        this.ttl  = ttl
        this.imgByte = imgByte

        if(date == null) {
            this.date = Date()

            // Set expirnation date.
            val cl:Calendar = Calendar.getInstance()
            cl.setTime(this.date)
            cl.add(Calendar.DAY_OF_MONTH, mapExpir.get(ttl)!!)

            this.date = cl.time
        }else{
            this.date = date
        }


    }

    fun printMyStatus(){
        Log.v("test", "Name: ${this.name}, Expir: ${date}, URI: ${this.imgByte}")
    }

}