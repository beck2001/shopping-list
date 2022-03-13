package kz.iitu.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*
import kz.iitu.shoppinglist.R
import kz.iitu.shoppinglist.data.db.entity.ShoppingItem

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        text_add.setOnClickListener {
            val name = edit_text_name.text.toString()
            val amount = edit_text_amount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter required info", Toast.LENGTH_SHORT).show()
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        text_cancel.setOnClickListener {
            cancel()
        }
    }
}