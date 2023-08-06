package com.vsanto.notas

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val notes = mutableListOf(
        Note("Titulo texto", Type.Text),
        Note("Titulo lista", Type.List)
    )

    private lateinit var rvNotes: RecyclerView
    private lateinit var noteAdapter: NoteAdapter

    private lateinit var cvCreateList: CardView
    private lateinit var cvCreateTxt: CardView

    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        rvNotes = findViewById(R.id.rvNotes)
        fabAdd = findViewById(R.id.fabAdd)
    }

    private fun initListeners() {
        fabAdd.setOnClickListener { showCreateNoteDialog() }
    }

    private fun initUI() {
        noteAdapter = NoteAdapter(notes)
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = noteAdapter
    }

    private fun showCreateNoteDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_create_note)

        cvCreateList = dialog.findViewById(R.id.cvCreateList)
        cvCreateList.setOnClickListener { createNote(Type.List) }
        cvCreateTxt = dialog.findViewById(R.id.cvCreateTxt)
        cvCreateTxt.setOnClickListener { createNote(Type.Text) }

        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.window?.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )

        dialog.show()
    }

    private fun createNote(type: Type) {
        notes.add(Note("Nuevo", type))
        noteAdapter.notifyDataSetChanged()
    }
}