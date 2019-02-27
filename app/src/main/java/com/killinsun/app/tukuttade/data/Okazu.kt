package com.killinsun.app.tukuttade.data

import android.util.Log
import java.util.*
import java.util.Calendar.DATE

class Okazu(okazuName:String, ttl:Int?, date: Date?, imgByte: ByteArray?) {

    val id:String
    var name:String
    var ttl:Int?
    var imgByte: ByteArray?
    var expireDate: Date
    val mapExpir:Map<Int, Int> = mapOf(
        0 to 0, //index:0 今日中
        1 to 3, //index:1 三日後
        2 to 7  //index:2 一週間後
    )


    init{
        this.id = "not initialized"
        this.name = okazuName
        this.ttl  = ttl
        this.imgByte = imgByte

        val baseDate = Date()
        //dateがNullの場合はTTL値から何日後かを導く
        if(date == null && ttl != null) {
            Log.v("test","Calc start!")
            this.expireDate = calcDateFromTtl(baseDate, ttl)
        }else if(date != null){
            this.expireDate = date
        }else {
            this.expireDate = baseDate
        }

    }

    fun calcDateFromTtl(baseDate: Date, ttl: Int): Date{

        val cl:Calendar = Calendar.getInstance()
        cl.setTime(baseDate)
        cl.add(DATE, mapExpir.get(ttl)!!)
        Log.v("test", "TTL: ${ttl}, mapExpire:${mapExpir.get(ttl)}")
        return cl.getTime()

    }
}