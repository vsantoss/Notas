package com.vsanto.notas.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vsanto.notas.MainActivity
import com.vsanto.notas.Note
import com.vsanto.notas.R

class ListActivity : AppCompatActivity() {

    private val items = mutableListOf(
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3", true)
    )

    private lateinit var note: Note
    private lateinit var etTitle: EditText
    private lateinit var rvItems: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var fabAddItem: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        note = intent.getSerializableExtra(MainActivity.NOTE_KEY) as Note
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        etTitle = findViewById(R.id.etTitle)
        rvItems = findViewById(R.id.rvItems)
        fabAddItem = findViewById(R.id.fabAddItem)
    }

    private fun initListeners() {
        etTitle.doAfterTextChanged { note.title = etTitle.text.toString() }
        fabAddItem.setOnClickListener { createItem() }
    }

    private fun initUI() {
        etTitle.setText(note.title)

        itemAdapter =
            ItemAdapter(items, { pos -> onItemSelected(pos) }, { pos -> deleteItem(pos) })
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = itemAdapter
    }

    private fun createItem() {
        items.add(Item(""))
        orderItems()
        itemAdapter.notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        items.removeAt(position)
        itemAdapter.notifyDataSetChanged()
    }

    private fun onItemSelected(position: Int) {
        items[position].isSelected = !items[position].isSelected
        orderItems()
        itemAdapter.notifyDataSetChanged()
    }

    private fun orderItems() {
        items.sortBy { item -> item.isSelected }
    }

}