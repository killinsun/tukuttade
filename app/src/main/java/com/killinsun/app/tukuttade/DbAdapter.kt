package com.killinsun.app.tukuttade

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

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
        values.put("bitmap", okazu.imgBm.toString())

        db.insertOrThrow(DB_TABLE_NAME, null, values)
    }

    //とりあえず全件取得
    fun getOkazuTest(): ArrayList<String>{

        val sql: String = "SELECT * FROM " + DB_TABLE_NAME +";"
        val cursor: Cursor = db.rawQuery(sql,null)

        var okazuArray: ArrayList<Okazu> = arrayListOf()
        var res: ArrayList<String> = arrayListOf()


        try{
            for(i in 0..cursor.count){
                if(cursor.moveToNext()){
                    res.add(cursor.getString(cursor.getColumnIndex("name")))
                }
            }
        } finally{
            cursor.close()
        }

        return res
    }

}