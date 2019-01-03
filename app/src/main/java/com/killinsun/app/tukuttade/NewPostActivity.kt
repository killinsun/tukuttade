package com.killinsun.app.tukuttade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_new_post.*

class NewPostActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        var listItems = arrayOf(0,1)

        addRecyclerView.layoutManager = LinearLayoutManager(this)
        addRecyclerView.adapter = NewPostAdapter(listItems)

    }
}