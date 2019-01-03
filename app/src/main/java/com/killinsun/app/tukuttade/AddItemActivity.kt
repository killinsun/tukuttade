package com.killinsun.app.tukuttade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_new_item.*

class AddItemActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)



        addRecyclerView.layoutManager = LinearLayoutManager(this)
        addRecyclerView.adapter = AddItemAdapter()

    }
}