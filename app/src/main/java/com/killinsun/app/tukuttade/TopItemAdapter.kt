package com.killinsun.app.tukuttade

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TopItemAdapter(private val listItems:ArrayList<Okazu>,
                     private val ItemListener: TopItemViewHolder.ItemInterface)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        view = layoutInflater.inflate(R.layout.layout_top_item_ib, parent, false)
        return TopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val myHolder: TopItemViewHolder = holder as TopItemViewHolder

        myHolder.ibtnItem.setOnClickListener{
           ItemListener.onClickItem(position)
        }

        //TODO: imgByte!! の部分どうにかする
        val bmp: Bitmap = BitmapFactory.decodeByteArray(
            listItems[position].imgByte, 0, listItems[position].imgByte!!.size
        )
        myHolder.ibtnItem.setImageDrawable(BitmapDrawable(bmp))
    }

    override fun getItemCount(): Int{
        return listItems.size
    }
}
