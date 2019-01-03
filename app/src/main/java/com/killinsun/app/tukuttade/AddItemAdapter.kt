package com.killinsun.app.tukuttade

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.layout_new_item_card.view.*
import kotlin.math.log

class AddItemAdapter()
    : RecyclerView.Adapter<AddItemViewHolder>(){

    private val expirationArray = arrayOf("今日中","三日後","一週間後")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_new_item_card, parent, false)

        view.spiExpiration.adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,expirationArray)

        return AddItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder?, position: Int) {
        holder?.ibtnCamera?.setOnClickListener{
            Log.v("test","Camera button clicked!!!")
        }

    }


    // カメラボタンを押した時の処理
    fun selectItemImage(){
        Log.v("test", "log_test")
    }

    // 期限スピナーを押した時の処理


    override fun getItemCount(): Int{
        return 1
    }

}

