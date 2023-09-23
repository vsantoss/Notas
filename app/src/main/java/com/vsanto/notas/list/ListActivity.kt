package com.vsanto.notas.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.vsanto.notas.MainActivity
import com.vsanto.notas.Note
import com.vsanto.notas.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private val items = mutableListOf(
        Item("Item 1"), Item("Item 2"), Item("Item 3", true)
    )

    private lateinit var binding: ActivityListBinding

    private lateinit var note: Note

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        note = intent.getSerializableExtra(MainActivity.NOTE_KEY) as Note

        initListeners()
        initUI()
    }

    private fun initListeners() {
        binding.etTitle.doAfterTextChanged { note.title = binding.etTitle.text.toString() }
        binding.fabAddItem.setOnClickListener { createItem() }
    }

    private fun initUI() {
        binding.etTitle.setText(note.title)

        itemAdapter = ItemAdapter(
            items,
            onItemSelected = { pos -> onItemSelected(pos) },
            onItemDeleted = { pos -> deleteItem(pos) }
        )

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = itemAdapter
    }

    private fun createItem() {
        items.add(Item(""))
        orderItems()
        itemAdapter.notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        items.removeAt(position)
        itemAdapter.notifyItemRemoved(position)
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