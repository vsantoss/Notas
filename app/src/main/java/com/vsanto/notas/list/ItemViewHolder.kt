package com.vsanto.notas.list

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.vsanto.notas.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val cbItem: CheckBox = view.findViewById(R.id.cbItem)
    private val etItem: EditText = view.findViewById(R.id.etItem)

    fun render(item: Item) {
        etItem.setText(item.name)

        cbItem.isChecked = item.isSelected
        if (item.isSelected) {
            etItem.paintFlags = etItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            etItem.paintFlags = etItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

}