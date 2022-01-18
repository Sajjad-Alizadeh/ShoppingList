package com.sajjad.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sajjad.shoppinglist.data.db.entity.ShopItem

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShopItem)

    @Delete
    suspend fun deleteItem(item: ShopItem)

    @Query("SELECT * FROM ShopItem")
    fun getItems():LiveData<List<ShopItem>>
}