package com.killinsun.app.tukuttade

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton

class TopItemViewHolder (view: View): RecyclerView.ViewHolder(view){

    interface ItemInterface{
        fun onClickItem(position: Int)
    }

    val ibtnItem: ImageButton = view.findViewById(R.id.ibtnItem)
}

