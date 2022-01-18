package com.sajjad.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sajjad.shoppinglist.data.db.entity.ShopItem

@Database(
    entities = [ShopItem::class],
    version = 1
)
abstract class ShopDataBase : RoomDatabase() {

    abstract fun getDao(): ShoppingListDao

    companion object {
        /**
         * @Volatile:
         *  make sure that one thread at a time is writing to that instance.
         *  otherwise that could be that there are two threads and both want to initialize that instance variable at the same time.
         *  and then we would have two instances of the same shopping db which we don't want here
         */
        @Volatile
        var instance: ShopDataBase? = null
        val LOCK = Any()

        /**
         * operator fun invoke:
         * this fun is executed whenever we create an instance of our ShopDataBase class {like ShopDataBase()}
         */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            //to make sure that no other threads will access this instance and set it at the same time. for that we have to create a lock object
            instance ?: createDataBase(context).also {
                // we set our instance after the calling the fun
                instance = it
            }
        }


        private fun createDataBase(context: Context):ShopDataBase {
            // to instantiate our actual db
            return Room.databaseBuilder(context.applicationContext, ShopDataBase::class.java, "shopDB.db")
                .build()
        }


    }
}