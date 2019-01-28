package com.killinsun.app.tukuttade

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.ByteArrayInputStream
import java.text.SimpleDateFormat
import java.util.*

class DbAdapter(mContext: Context) {
    private val db: SQLiteDatabase
    private val tsukuttadeDB: DbHelper

    init {
        tsukuttadeDB = DbHelper(mContext)
        db = tsukuttadeDB.writableDatabase
    }

    fun addRecord(okazu:Okazu){
        val values = ContentValues()
        values.put("name", okazu.name)
        values.put("date", okazu.date.toString())
        values.put("image", okazu.imgByte)

        db.insertOrThrow(DB_TABLE_NAME, null, values)
    }

    //とりあえず全件取得
    fun getOkazuTest(): ArrayList<Okazu>{

        val sql: String = "SELECT * FROM " + DB_TABLE_NAME +";"
        val cursor: Cursor = db.rawQuery(sql,null)

        var okazuArray: ArrayList<Okazu> = arrayListOf()

        try{
            for(i in 0..cursor.count){
                if(cursor.moveToNext()){
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val dateLong = cursor.getLong(cursor.getColumnIndex("date"))
                    val imgByte = cursor.getBlob(cursor.getColumnIndex("image"))

                    //Long型の日付数値を読める日付型に変換
                    //Todo 別のクラス作ってそこで処理まとめる
                    val date:Date = Date(dateLong)
                    val fmter: SimpleDateFormat = SimpleDateFormat("yyyy/mm/dd")

                    //確認用
                    val dateString: String
                    dateString= fmter.format(date)
                    Log.v("test",dateString)

                    val okazu:Okazu = Okazu(name, null, date, imgByte)
                    okazuArray.add(okazu)
                }
            }
        } finally{
            cursor.close()
        }

        return okazuArray
    }

}