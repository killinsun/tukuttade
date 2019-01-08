package com.killinsun.app.tukuttade

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_post.*

class NewPostActivity : AppCompatActivity(), NewPostViewHolder.NewPostInterface, NewItemViewHolder.NewItemInterface{

    private var listItems: ArrayList<Int> = arrayListOf(0)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        addRecyclerView.layoutManager = LinearLayoutManager(this)
        addRecyclerView.adapter = NewPostAdapter(listItems,this, this)

        btnAdd.setOnClickListener{ onClickDoyaButton()}

    }

    override fun onClickCameraButton(position: Int) {
        val RESULT_PICK_IMGFILE = position;
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, RESULT_PICK_IMGFILE)
    }

    override fun onClickAddButton() {
        listItems.add(listItems.size)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
       if(resultCode == Activity.RESULT_OK){
           val holder:NewPostViewHolder = addRecyclerView.findViewHolderForAdapterPosition(requestCode) as NewPostViewHolder

           val uri = data?.data
           val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri)
           holder.ibtnCamera?.setImageBitmap(bitmap)
       }
    }

    fun onClickDoyaButton(){

        for(count:Int in 0..listItems.size-1){
            val holder:NewPostViewHolder = addRecyclerView.findViewHolderForAdapterPosition(count) as NewPostViewHolder
            Log.v("test",holder.edtTitleName?.text.toString())
            Log.v("test",holder.spiExpiration?.selectedItem.toString())
        }
    }

}