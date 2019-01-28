package com.killinsun.app.tukuttade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TopItemViewHolder.ItemInterface {

    lateinit private var tsukuDB : DbAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tsukuDB = DbAdapter(this)
        setContentView(R.layout.activity_main)

        val OkazuArray:ArrayList<Okazu> = tsukuDB.getOkazuTest()
        for(okazu in OkazuArray){
            Log.v("test","------------------")
            Log.v("test", "okazu name: " + okazu.name)
            Log.v("test", "okazu date: " + okazu.date.toString())

        }


        top_recycler.layoutManager = GridLayoutManager(this,3)
        val okazuArray = tsukuDB.getOkazuTest()
        top_recycler.adapter = TopItemAdapter(okazuArray, this)

        fab_add.setOnClickListener{
            Toast.makeText(this,"FAB Clicked!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, NewPostActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onClickItem(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
