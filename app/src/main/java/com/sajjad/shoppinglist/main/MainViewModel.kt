package com.sajjad.shoppinglist.main

import androidx.lifecycle.ViewModel
import com.sajjad.shoppinglist.data.db.entity.ShopItem
import com.sajjad.shoppinglist.data.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ShoppingRepository):ViewModel() {

    fun insert(item: ShopItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(item)
    }

    fun delete(item: ShopItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }

    fun getItems() = repository.getItems()
}