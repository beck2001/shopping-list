package kz.iitu.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item.view.*
import kz.iitu.shoppinglist.R
import kz.iitu.shoppinglist.data.db.entity.ShoppingItem
import kz.iitu.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(var items: List<ShoppingItem>, private val shoppingViewModel: ShoppingViewModel) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.itemView.text_name.text = currentShoppingItem.name
        holder.itemView.text_amount.text = "${currentShoppingItem.amount}"

        holder.itemView.image_delete.setOnClickListener {
            shoppingViewModel.delete(currentShoppingItem)
        }

        holder.itemView.image_minus.setOnClickListener {
            currentShoppingItem.amount--
            shoppingViewModel.upsert(currentShoppingItem)
        }

        holder.itemView.image_plus.setOnClickListener {
            if (currentShoppingItem.amount > 0) {
                currentShoppingItem.amount++
                shoppingViewModel.upsert(currentShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}