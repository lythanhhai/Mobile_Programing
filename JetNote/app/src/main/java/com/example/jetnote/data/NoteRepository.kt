package com.example.jetnote.data

import com.example.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.flowOn

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao){
    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.update(note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.delete(note)
    suspend fun deleteAllNote(note: Note) = noteDatabaseDao.deleteAll()
    fun getAllNote(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO)


}