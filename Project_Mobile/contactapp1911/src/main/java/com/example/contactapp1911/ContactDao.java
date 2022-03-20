package com.example.contactapp1911;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * From Contact")
    List<Contact> getAllContacts();

    @Insert
    void insertAll(Contact... contact);
    
    // xóa tất cả
    @Query("DELETE From Contact")
    public void nullTable();

    @Query("Select * From Contact where name = :name")
    List<Contact> getContactsByName(String name);

    @Insert
    void insertContact(Contact contact);

    @Update
    void updateContact(Contact contact);
}
