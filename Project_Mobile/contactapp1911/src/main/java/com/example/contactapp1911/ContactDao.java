package com.example.contactapp1911;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * From Contact")
    List<Contact> getAllContacts();

    @Insert
    void insertAll(Contact... contact);
}
