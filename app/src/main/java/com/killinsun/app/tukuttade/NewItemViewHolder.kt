package com.killinsun.app.tukuttade

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton

class NewItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

    interface NewItemInterface{
        fun onClickAddButton()
    }

    // 最後の行だった場合はこっちを読み込む
    val ibtnItemAdd: ImageButton = view.findViewById(R.id.ibtnItemAdd)
}