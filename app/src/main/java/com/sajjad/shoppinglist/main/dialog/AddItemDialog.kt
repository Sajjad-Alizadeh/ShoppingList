package com.sajjad.shoppinglist.main.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.sajjad.shoppinglist.R
import com.sajjad.shoppinglist.data.db.entity.ShopItem
import kotlinx.android.synthetic.main.dialog_add_item.*

class AddItemDialog(context: Context, val callBack: AddItemCallBack) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_item)

        tvAdd.setOnClickListener {
            val item = ShopItem(etName.text.toString(), etAmount.text.toString().toInt())
            callBack.onAddBtnClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            dismiss()
        }
    }

    interface AddItemCallBack {
        fun onAddBtnClicked(item: ShopItem)
    }
}