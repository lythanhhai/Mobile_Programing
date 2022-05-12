package com.example.jetnote.data

import androidx.room.*
import com.example.jetnote.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("Select * from notes")
    fun getNotes(): Flow<List<Note>>

    @Query("Select * From notes Where id=:id")
    suspend fun getNoteById(id: String):  Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("Delete from notes")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)
}