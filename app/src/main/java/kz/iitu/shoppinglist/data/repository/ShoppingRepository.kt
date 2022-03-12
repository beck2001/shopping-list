package kz.iitu.shoppinglist.data.repository

import kz.iitu.shoppinglist.data.db.ShoppingDatabase
import kz.iitu.shoppinglist.data.db.entity.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}