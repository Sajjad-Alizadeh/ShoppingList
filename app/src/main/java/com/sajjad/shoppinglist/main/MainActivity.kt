package com.sajjad.shoppinglist.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.shoppinglist.R
import com.sajjad.shoppinglist.data.db.entity.ShopItem
import com.sajjad.shoppinglist.main.adapter.ShoppingAdapter
import com.sajjad.shoppinglist.main.dialog.AddItemDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    val shoppingAdapter: ShoppingAdapter by instance()
    val viewModelFactory: MainViewModelFactory by instance()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getItems().observe(this, {
            shoppingAdapter.items = it
            notifyList()
        })

        shoppingAdapter.callBack = object :ShoppingAdapter.ShoppingCallBack{
            override fun onIncreaseBtnClicked(item: ShopItem) {
                item.count++
                viewModel.insert(item)
            }

            override fun onDecreaseBtnClicked(item: ShopItem) {
                if (item.count > 1) {
                    item.count--
                    viewModel.insert(item)
                } else {
                    Toast.makeText(this@MainActivity, "can't decrease", Toast.LENGTH_LONG).show()
                }
            }

            override fun onDeleteBtnClicked(item: ShopItem) {
                viewModel.delete(item)
            }

        }

        rv_shopping_item.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_shopping_item.adapter = shoppingAdapter

        fab_add.setOnClickListener {
            AddItemDialog(this,object:AddItemDialog.AddItemCallBack{
                override fun onAddBtnClicked(item: ShopItem) {
                    viewModel.insert(item)
                }

            }).show()
        }

    }

    private fun notifyList() {
        shoppingAdapter.notifyDataSetChanged()
    }




}