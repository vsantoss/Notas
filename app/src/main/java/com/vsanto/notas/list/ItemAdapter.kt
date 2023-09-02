package com.vsanto.notas.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.vsanto.notas.R

class ItemAdapter(
    var items: List<Item>,
    private val onItemSelected: (Int) -> Unit,
    private val onItemDeleted: (Int) -> Unit
) :
    RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.render(items[position])

        val cbItem: CheckBox = holder.itemView.findViewById(R.id.cbItem)
        cbItem.setOnClickListener { onItemSelected(position) }

        val ivCloseItem: ImageView = holder.itemView.findViewById(R.id.ivCloseItem)
        ivCloseItem.setOnClickListener { onItemDeleted(position) }
    }

    override fun getItemCount() = items.size
}