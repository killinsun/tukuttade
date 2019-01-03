package com.killinsun.app.tukuttade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_add.setOnClickListener{
            Toast.makeText(this,"FAB Clicked!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }

    }
}
