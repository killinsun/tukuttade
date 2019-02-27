package com.killinsun.app.tukuttade.okazuAddEdit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.FirebaseApp
import com.killinsun.app.tukuttade.*
import kotlinx.android.synthetic.main.act_new_post.*
import android.provider.MediaStore

class NewPostActivity : AppCompatActivity(), NewPostContract.View, NewPostViewHolder.NewPostInterface,
    NewItemViewHolder.NewItemInterface {

    override lateinit var presenter: NewPostContract.Presenter
    override var isActive: Boolean = false

    private val db: FbHelper = FbHelper()

    override fun onResume(){
        super.onResume()
        presenter.start()
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_new_post)
        FirebaseApp.initializeApp(this)

        newPostRecyclerView.layoutManager = LinearLayoutManager(this)

        // Create the presenter
        NewPostPresenter( this, newPostRecyclerView)
        presenter.initRecyclerView(this,this)

        btnAdd.setOnClickListener{ onClickDoyaButton()}

    }

    override fun setLoadingIndicator(active: Boolean) {
        if(active){

        }
    }

    //カメラアイコン押されたらギャラリーから画像選ばせる
    override fun onClickCameraButton(position: Int) {
        val RESULT_PICK_IMGFILE = position;
        val intent:Intent = presenter.selectOkazuImage()
        startActivityForResult(intent, RESULT_PICK_IMGFILE)
    }

    //プラスボタン押されたらOkazuインスタンス生成してリスト追加
    override fun onClickAddButton() {
        presenter.addPostItem()
    }

    //ギャラリーから画像選んだ結果処理
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.result(requestCode, resultCode, data)
    }

    override fun showOkazuImage(uri: Uri?, position:Int) {

        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri)
        val holder: NewPostViewHolder =
            newPostRecyclerView.findViewHolderForAdapterPosition(position) as NewPostViewHolder
        holder.ibtnCamera?.setImageBitmap(bitmap)
    }

    private fun onClickDoyaButton() {
        presenter.submitItems()
    }

    /*
    companion object{
        private val REQUEST_SELECT_IMG = 1
    }
    */

}