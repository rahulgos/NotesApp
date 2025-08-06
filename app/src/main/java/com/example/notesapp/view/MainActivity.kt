package com.example.notesapp.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.NoteEntity
import com.example.notesapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), NotesRVAdapter {

    // ViewModel to interact with our data
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup RecyclerView with a linear layout and adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this, this)
        recyclerView.adapter = adapter

        // Get instance of our ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        // Observe LiveData to reflect note changes in UI automatically
        viewModel.allNotes.observe(this, Observer {
            it?.let { adapter.updateList(it) }
        })
    }

    // Called when a note item is clicked in the adapter (deletes the note)
    override fun onItemClicked(noteEntity: NoteEntity) {
        viewModel.deleteNote(noteEntity)
        Toast.makeText(this, "${noteEntity.text} has been deleted", Toast.LENGTH_SHORT).show()
    }

    // Called when the user taps the Add button to submit a new note
    fun submitNote(view: View) {
        val inputText = findViewById<EditText>(R.id.inputText)
        val noteText = inputText.text.toString().trim()

        if (noteText.isNotEmpty()) {
            // Insert the new note
            viewModel.insertNote(NoteEntity(noteText))

            // Clear the input box
            inputText.text.clear()

            // Hide the keyboard after submitting
            val hideKeyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKeyBoard.hideSoftInputFromWindow(view.windowToken, 0)

            // Show confirmation
            Toast.makeText(this, "$noteText has been added", Toast.LENGTH_SHORT).show()
        }
    }
}
