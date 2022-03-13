package kz.iitu.shoppinglist.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping.*
import kz.iitu.shoppinglist.R
import kz.iitu.shoppinglist.data.db.ShoppingDatabase
import kz.iitu.shoppinglist.data.db.entity.ShoppingItem
import kz.iitu.shoppinglist.data.repository.ShoppingRepository
import kz.iitu.shoppinglist.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val db = ShoppingDatabase(this)
        val repository = ShoppingRepository(db)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        recycler_shopping_items.layoutManager = LinearLayoutManager(this)
        recycler_shopping_items.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        floating_button_add.setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}