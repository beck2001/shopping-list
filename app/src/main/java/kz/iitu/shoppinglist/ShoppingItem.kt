package kz.iitu.shoppinglist

import androidx.room.Entity

@Entity(tableName = "shopping_item")
data class ShoppingItem(var name: String, var amount: Int) {
}