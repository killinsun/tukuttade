package com.killinsun.app.tukuttade

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.layout_new_item_card.view.*

class NewPostAdapter(private val itemList:Array<Int>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val expirationArray = arrayOf("今日中","三日後","一週間後")

    override fun getItemViewType(position: Int) : Int{
        Log.v("test","!!!!!:  $itemList.size")
        return if(position == itemList.size) 0 else 1
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
        if(position == itemList.size){
            /*
            holder.ibtnItemAdd.setOnClickListener{
                Log.v("test","Add button clicked!!!")
            }
            */
        }else {
            /*
            holder.ibtnCamera.setOnClickListener {
                Log.v("test", "Camera button clicked!!!")
            }
            */
        }

    }


    // カメラボタンを押した時の処理
    fun selectItemImage(){
        Log.v("test", "log_test")
    }

    override fun getItemCount(): Int{
        return itemList.size + 1
    }

}

