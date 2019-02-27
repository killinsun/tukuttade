package com.killinsun.app.tukuttade.okazuAddEdit

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import com.killinsun.app.tukuttade.data.Okazu
import java.io.ByteArrayOutputStream
import java.util.*
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.net.Uri
import android.util.Log


class NewPostPresenter(
    private val okazuView: NewPostContract.View,
    private val newPostRecyclerView: RecyclerView
    ) : NewPostContract.Presenter{

    private val okazuArray: ArrayList<Okazu> = arrayListOf()

    init {
        okazuView.presenter = this
    }

    override fun start(){
        loadPostItems(false)
    }

    override fun result(requestCode: Int, resultCode: Int, data: Intent?){
        if(resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            okazuView.showOkazuImage(uri, requestCode)
        }
    }

    override fun loadPostItems(forceUpdate: Boolean){

    }

    override fun initRecyclerView(
        newPostListener: NewPostViewHolder.NewPostInterface,
        newItemListener: NewItemViewHolder.NewItemInterface
    ) {
        val okazu = Okazu("",null,null,null)
        okazuArray.add(okazu)
        newPostRecyclerView.adapter = NewPostAdapter(okazuArray, newPostListener, newItemListener)

    }

    override fun addPostItem(){
        val okazu: Okazu = Okazu("", 0, null, null)
        okazuArray.add(okazu)
    }

    override fun deletePostItem(position: Int){

    }

    override fun submitItems() {
        for (cnt: Int in 0..okazuArray.size - 1) {
            val holder: NewPostViewHolder = newPostRecyclerView.findViewHolderForAdapterPosition(cnt) as NewPostViewHolder
            okazuArray[cnt].name     = holder.edtTitleName?.text.toString()
            okazuArray[cnt].ttl      = holder.spiExpiration!!.selectedItemPosition

            //おかず消費期限の設定
            //TODO: 汚いから綺麗にしたい　
            val d = Date()
            okazuArray[cnt].expireDate = okazuArray[cnt].calcDateFromTtl(d, okazuArray[cnt].ttl!!)

            //ImageView/ImageButton -> BitmapDrawable -> ByteArrayOutputStream
            val drawable: BitmapDrawable = holder.ibtnCamera?.drawable as BitmapDrawable
            val iba = getImgByteArray(drawable)

            //Upload to FirebaseStorage
            /*
            val childName = "${okazuArray[cnt].name}_${okazuArray[cnt].expireDate}.png"
            uploadToFb(iba, childName)


            db.create(listItems[cnt])
            */
        }
    }

    override fun selectOkazuImage(): Intent{
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        return intent
    }

    private fun getImgByteArray(drawable:BitmapDrawable): ByteArray{
        val bitmap: Bitmap = drawable.bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos)
        return baos.toByteArray()
    }

}