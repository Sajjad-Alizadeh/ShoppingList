package com.sajjad.shoppinglist.data.repository

import com.sajjad.shoppinglist.data.db.ShopDataBase
import com.sajjad.shoppinglist.data.db.entity.ShopItem

class ShoppingRepository(private val shopDataBase: ShopDataBase) {

    suspend fun insert(item:ShopItem) = shopDataBase.getDao().insertItem(item)

    suspend fun delete(item:ShopItem) = shopDataBase.getDao().deleteItem(item)

    fun getItems() = shopDataBase.getDao().getItems()
}