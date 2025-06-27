package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val dao = NoteDatabase.getDatabase(application).noteDao()
    val notes = dao.getAll().asLiveData()

    fun addNote(title: String, desc: String) {
        viewModelScope.launch {
            dao.insert(Note(title = title, desc = desc))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            dao.delete(note)
        }
    }
}