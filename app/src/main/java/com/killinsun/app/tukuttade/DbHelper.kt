package com.killinsun.app.tukuttade

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val DB_NAME:String = "tsukuttade"
val DB_VERSION:Int = 1 //自分でつけるバージョン。構造が変わったら上げる
val DB_TABLE_NAME:String = "okazu_table"

class DbHelper (var mContext: Context?): SQLiteOpenHelper(mContext, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE " + DB_TABLE_NAME + " ( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name text not null, " +
                    "date date not null, " +
                    "bitmap text " +
                    ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db?.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME + ";")
        onCreate(db)
    }

}