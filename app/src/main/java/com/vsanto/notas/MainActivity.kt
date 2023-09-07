package com.vsanto.notas

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.vsanto.notas.databinding.ActivityMainBinding
import com.vsanto.notas.list.ListActivity
import com.vsanto.notas.text.TextActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val notes = mutableListOf(
        Note("Titulo texto", Type.Text, Calendar.getInstance().time, Calendar.getInstance().time),
        Note("Titulo lista", Type.List, Calendar.getInstance().time, Calendar.getInstance().time)
    )

    companion object {
        const val NOTE_KEY = "NOTE"
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var noteAdapter: NoteAdapter

    private lateinit var cvCreateList: CardView
    private lateinit var cvCreateTxt: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initUI()
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener { showCreateNoteDialog() }
    }

    private fun initUI() {
        noteAdapter = NoteAdapter(notes) { position -> onNoteSelected(position) }
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = noteAdapter
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Notas"
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
            ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT
        )

        dialog.show()
    }

    private fun createNote(type: Type) {
        notes.add(Note("Nuevo", type, Calendar.getInstance().time, Calendar.getInstance().time))
        noteAdapter.notifyDataSetChanged()
    }

    private fun onNoteSelected(position: Int) {
        val note = notes[position]
        when (note.type) {
            Type.List -> navigateToNote(note, ListActivity::class.java)
            Type.Text -> navigateToNote(note, TextActivity::class.java)
        }
    }

    private fun navigateToNote(note: Note, noteClass: Class<*>) {
        val intent = Intent(this, noteClass)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

}