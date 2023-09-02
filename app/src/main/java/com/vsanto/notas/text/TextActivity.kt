package com.vsanto.notas.text

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.vsanto.notas.MainActivity.Companion.NOTE_KEY
import com.vsanto.notas.Note
import com.vsanto.notas.R

class TextActivity : AppCompatActivity() {

    private lateinit var note: Note
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        note = intent.getSerializableExtra(NOTE_KEY) as Note
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
    }

    private fun initListeners() {
        etTitle.doAfterTextChanged { note.title = etTitle.text.toString() }
    }

    private fun initUI() {
        etTitle.setText(note.title)
    }

}