package com.killinsun.app.tukuttade.okazuList

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import com.killinsun.app.tukuttade.R

class TopItemViewHolder (view: View): RecyclerView.ViewHolder(view){

    interface ItemInterface{
        fun onClickItem(position: Int)
    }

    val ibtnItem: ImageButton = view.findViewById(R.id.ibtnItem)
}

