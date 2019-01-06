package com.killinsun.app.tukuttade

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner

class NewPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    interface NewPostInterface{
        fun onClickCameraButton(position: Int)
    }
    // 最後の行以外の場合はこっちを読み込む。　
    val edtTitleName: EditText? = view.findViewById(R.id.edtName)
    val spiExpiration: Spinner? = view.findViewById(R.id.spiExpiration)
    val ibtnCamera: ImageButton? = view.findViewById(R.id.ibtnCamera)


}