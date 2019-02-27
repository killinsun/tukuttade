package com.killinsun.app.tukuttade.okazuAddEdit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.killinsun.app.tukuttade.R
import com.killinsun.app.tukuttade.data.Okazu
import kotlinx.android.synthetic.main.layout_new_item_card.view.*

class NewPostAdapter(
    private val okazuArray:ArrayList<Okazu>,
    private val newPostListener: NewPostViewHolder.NewPostInterface,
    private val newItemListener: NewItemViewHolder.NewItemInterface
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val expirationArray = arrayOf("今日中","３日以内","１周間以内")

    override fun getItemViewType(position: Int) : Int{
        return if(position == okazuArray.size) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        if(viewType == 0){
            //最後の行の場合は追加ボタンレイアウトを読み込む
            view = layoutInflater.inflate(R.layout.layout_add_button, parent, false)
            return NewItemViewHolder(view)
        }else {
            //最後の行以外の場合はそのままカードを読み込む
            view = layoutInflater.inflate(R.layout.layout_new_item_card, parent, false)
            view.spiExpiration.adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,expirationArray)
            return NewPostViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == okazuArray.size){
            val myHolder: NewItemViewHolder = holder as NewItemViewHolder

            myHolder.ibtnItemAdd.setOnClickListener{
                newItemListener.onClickAddButton()
                notifyItemInserted(position + 1)
            }
        }else {
            val myHolder: NewPostViewHolder = holder as NewPostViewHolder
            myHolder.ibtnCamera?.setOnClickListener {
                newPostListener.onClickCameraButton(position)
            }
        }

    }

    override fun getItemCount(): Int{
        return okazuArray.size + 1
    }

}

