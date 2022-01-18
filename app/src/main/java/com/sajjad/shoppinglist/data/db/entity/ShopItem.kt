package com.sajjad.shoppinglist.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItem(
    val name:String,
    var count:Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int ?= null
}
