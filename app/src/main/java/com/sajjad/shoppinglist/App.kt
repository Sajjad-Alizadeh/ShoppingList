package com.sajjad.shoppinglist

import android.app.Application
import com.sajjad.shoppinglist.data.db.ShopDataBase
import com.sajjad.shoppinglist.data.repository.ShoppingRepository
import com.sajjad.shoppinglist.main.MainViewModelFactory
import com.sajjad.shoppinglist.main.adapter.ShoppingAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App :Application(),KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton { ShoppingAdapter() }

        bind() from singleton {
            ShopDataBase(this@App)
        }

        bind() from singleton {
           ShoppingRepository(instance())
        }

        bind() from provider {
            MainViewModelFactory(instance(),this@App)
        }
    }
}