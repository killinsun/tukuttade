package com.killinsun.app.tukuttade

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_new_post.*
import java.io.ByteArrayOutputStream
import java.util.*

class NewPostActivity : AppCompatActivity(), NewPostViewHolder.NewPostInterface, NewItemViewHolder.NewItemInterface{

    private var listItems: ArrayList<Okazu> = arrayListOf()
    private val db: FbHelper = FbHelper()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_post)
        FirebaseApp.initializeApp(this)


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
        for (cnt: Int in 0..listItems.size - 1) {
            val holder: NewPostViewHolder = addRecyclerView.findViewHolderForAdapterPosition(cnt) as NewPostViewHolder
            listItems[cnt].name     = holder.edtTitleName?.text.toString()
            listItems[cnt].ttl      = holder.spiExpiration!!.selectedItemPosition

            //おかず消費期限の設定
            //TODO: 汚いから綺麗にしたい　
            val d = Date()
            listItems[cnt].expireDate = listItems[cnt].calcDateFromTtl(d, listItems[cnt].ttl!!)

            //ImageView/ImageButton -> BitmapDrawable -> ByteArrayOutputStream
            val drawable:BitmapDrawable = holder.ibtnCamera?.drawable as BitmapDrawable
            val iba = getImgByteArray(drawable)

            //Upload to FirebaseStorage
            val childName = "${listItems[cnt].name}_${listItems[cnt].expireDate}.png"
            uploadToFb(iba, childName)


            db.create(listItems[cnt])

        }
    }
    private fun getImgByteArray(drawable:BitmapDrawable): ByteArray{
        val bitmap: Bitmap = drawable.bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos)
        return baos.toByteArray()
    }

    private fun uploadToFb(iba:ByteArray, child: String){

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imgRef = storageRef.child(child)
        val uploadTask = imgRef.putBytes(iba)

        uploadTask.addOnFailureListener {
            Log.e("test","Data upload error")
        }.addOnSuccessListener {
            Log.v("test", "Data uploaded!")
        }


    }

}