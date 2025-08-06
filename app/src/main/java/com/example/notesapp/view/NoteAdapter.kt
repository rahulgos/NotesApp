package com.example.notesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.NoteEntity

// Adapter class for binding a list of notes to the RecyclerView
class NoteAdapter(private val context: Context, private val listener: NotesRVAdapter) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // Local list to hold all current notes
    private val allNotes = ArrayList<NoteEntity>()

    // ViewHolder class that holds reference to views in each RecyclerView item
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        // Inflate layout for individual note item
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        val viewHolder = NoteViewHolder(view)

        // Delete button click event for each note
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // Bind the current note's text to the TextView
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    // This method updates the list and refreshes the adapter
    fun updateList(newList: List<NoteEntity>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

// Callback interface to handle note item click events (e.g., delete)
interface NotesRVAdapter {
    fun onItemClicked(noteEntity: NoteEntity)
}
