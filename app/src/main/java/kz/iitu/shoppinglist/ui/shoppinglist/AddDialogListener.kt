package kz.iitu.shoppinglist.ui.shoppinglist

import kz.iitu.shoppinglist.data.db.entity.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}