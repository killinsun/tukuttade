package com.killinsun.app.tukuttade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_post.*

class NewPostActivity : AppCompatActivity(), NewPostViewHolder.NewPostInterface{

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        var listItems: ArrayList<Int> = arrayListOf(0,1)

        addRecyclerView.layoutManager = LinearLayoutManager(this)
        addRecyclerView.adapter = NewPostAdapter(listItems,this)

    }

    override fun onClickCameraButton(position: Int) {
        Toast.makeText(this,"hello!", Toast.LENGTH_LONG).show()
    }
}