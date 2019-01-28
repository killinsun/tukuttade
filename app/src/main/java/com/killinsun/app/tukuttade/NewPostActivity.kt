package com.killinsun.app.tukuttade

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_new_post.*
import java.io.ByteArrayOutputStream

class NewPostActivity : AppCompatActivity(), NewPostViewHolder.NewPostInterface, NewItemViewHolder.NewItemInterface{

    lateinit private var tsukuDB : DbAdapter
    private var listItems: ArrayList<Okazu> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        tsukuDB = DbAdapter(this)
        setContentView(R.layout.activity_new_post)

        val okazu:Okazu = Okazu("",0,null, null)
        listItems.add(okazu)

        addRecyclerView.layoutManager = LinearLayoutManager(this)
        addRecyclerView.adapter = NewPostAdapter(listItems,this, this)

        btnAdd.setOnClickListener{ onClickDoyaButton()}

    }

    //カメラアイコン押されたらギャラリーから画像選ばせる
    override fun onClickCameraButton(position: Int) {
        val RESULT_PICK_IMGFILE = position;
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, RESULT_PICK_IMGFILE)
    }

    //プラスボタン押されたらOkazuインスタンス生成してリスト追加
    override fun onClickAddButton() {
        val okazu:Okazu = Okazu("",0,null, null)
        listItems.add(okazu)
    }

    //ギャラリーから画像選んだ結果処理
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
       if(resultCode == Activity.RESULT_OK){
           val holder:NewPostViewHolder = addRecyclerView.findViewHolderForAdapterPosition(requestCode) as NewPostViewHolder

           val uri = data?.data
           val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri)
           holder.ibtnCamera?.setImageBitmap(bitmap)
       }
    }

    private fun onClickDoyaButton() {
        for (count: Int in 0..listItems.size - 1) {
            val holder: NewPostViewHolder = addRecyclerView.findViewHolderForAdapterPosition(count) as NewPostViewHolder

            //画像のbitmapを得る
            val drawable:BitmapDrawable = holder.ibtnCamera?.drawable as BitmapDrawable
            val bitmap: Bitmap = drawable.bitmap

            //Byte型に変換する
            val bos: ByteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100, bos)
            val imgByte = bos.toByteArray()

            listItems[count].name = holder.edtTitleName?.text.toString()
            listItems[count].ttl = holder.spiExpiration!!.selectedItemPosition
            listItems[count].imgByte = imgByte
            listItems[count].printMyStatus()

            tsukuDB.addRecord(listItems[count])

        }
    }

}