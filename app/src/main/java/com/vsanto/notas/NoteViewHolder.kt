package com.vsanto.notas

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvNoteTitle: TextView = view.findViewById(R.id.tvNoteTitle)
    private val fabNote: FloatingActionButton = view.findViewById(R.id.fabNote)

    fun render(note: Note) {
        tvNoteTitle.text = note.title

        val drawable = when (note.type) {
            Type.List -> R.drawable.ic_check
            Type.Text -> R.drawable.ic_text
        }
        fabNote.setImageDrawable(
            ContextCompat.getDrawable(fabNote.context, drawable)
        )
    }

}