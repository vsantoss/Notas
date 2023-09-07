package com.vsanto.notas.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.vsanto.notas.MainActivity.Companion.NOTE_KEY
import com.vsanto.notas.Note
import com.vsanto.notas.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextBinding

    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        note = intent.getSerializableExtra(NOTE_KEY) as Note

        initListeners()
        initUI()
    }

    private fun initListeners() {
        binding.etTitle.doAfterTextChanged { note.title = binding.etTitle.text.toString() }
    }

    private fun initUI() {
        binding.etTitle.setText(note.title)
    }

}