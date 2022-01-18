package com.sajjad.shoppinglist.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sajjad.shoppinglist.data.repository.ShoppingRepository

class MainViewModelFactory(private val repository: ShoppingRepository, app: Application) :
    ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}