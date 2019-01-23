package com.killinsun.app.tukuttade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit private var tsukuDB : DbAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tsukuDB = DbAdapter(this)
        setContentView(R.layout.activity_main)

        for(test in tsukuDB.getOkazuTest()){
            Log.v("test", "Okazu: " + test)
        }

        fab_add.setOnClickListener{
            Toast.makeText(this,"FAB Clicked!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, NewPostActivity::class.java)
            startActivity(intent)

        }

    }
}
