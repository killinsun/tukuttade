package com.killinsun.app.tukuttade.okazuList

import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.killinsun.app.tukuttade.R
import com.killinsun.app.tukuttade.data.Okazu
import java.io.ByteArrayInputStream

class TopItemAdapter(private val listItems:ArrayList<Okazu>,
                     private val ItemListener: TopItemViewHolder.ItemInterface
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        view = layoutInflater.inflate(R.layout.layout_top_item_ib, parent, false)
        return TopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val myHolder: TopItemViewHolder = holder as TopItemViewHolder

        val bis = ByteArrayInputStream(listItems[position].imgByte)
        myHolder.ibtnItem.setImageDrawable(BitmapDrawable(myHolder.ibtnItem.resources, bis))

        myHolder.ibtnItem.setOnClickListener{
            ItemListener.onClickItem(position)
        }
    }

    override fun getItemCount(): Int{
        return listItems.size
    }
}
