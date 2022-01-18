package com.sajjad.shoppinglist.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.shoppinglist.R
import com.sajjad.shoppinglist.data.db.entity.ShopItem

class ShoppingAdapter() :
    RecyclerView.Adapter<ShoppingAdapter.MyViewHolder>() {

    var items:List<ShopItem> = listOf()
    var callBack: ShoppingCallBack?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName:TextView = itemView.findViewById(R.id.tvName)
        private val tvAmount:TextView = itemView.findViewById(R.id.tvAmount)
        private val minusBtn:ImageView = itemView.findViewById(R.id.ivMinus)
        private val deleteBtn:ImageView = itemView.findViewById(R.id.ivDelete)
        private val plusBtn:ImageView = itemView.findViewById(R.id.ivPlus)

        fun bind(item:ShopItem){
            tvName.text = item.name
            tvAmount.text = "${item.count}"

            plusBtn.setOnClickListener {
                callBack?.onIncreaseBtnClicked(item)
            }

            minusBtn.setOnClickListener {
                callBack?.onDecreaseBtnClicked(item)
            }

            deleteBtn.setOnClickListener {
                callBack?.onDeleteBtnClicked(item)
            }
        }
    }

    interface ShoppingCallBack{
        fun onIncreaseBtnClicked(item: ShopItem)
        fun onDecreaseBtnClicked(item: ShopItem)
        fun onDeleteBtnClicked(item: ShopItem)
    }
}