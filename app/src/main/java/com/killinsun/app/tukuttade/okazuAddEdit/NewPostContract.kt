package com.killinsun.app.tukuttade.okazuAddEdit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.widget.RecyclerView
import com.killinsun.app.tukuttade.BasePresenter
import com.killinsun.app.tukuttade.BaseView
import com.killinsun.app.tukuttade.data.Okazu


interface NewPostContract{

    interface View: BaseView<Presenter> {

        val isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun showOkazuImage(uri: Uri?, position: Int)

    }

    interface Presenter: BasePresenter {

        fun loadPostItems(forceUpdate: Boolean)

        fun initRecyclerView(newPostListener: NewPostViewHolder.NewPostInterface,
                             newItemListener: NewItemViewHolder.NewItemInterface)

        fun addPostItem()

        fun deletePostItem(position: Int)

        fun submitItems()

        fun selectOkazuImage(): Intent

        fun result(requestCode: Int, resultCode: Int, data: Intent?)
    }
}

