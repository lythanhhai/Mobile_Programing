package com.midterm.contacapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface contactDAO {

    @Query("SELECT * FROM contact")
    List<contact> getAllContacts();

    @Insert
    void insertAll(contact... contact);

    @Query("SELECT * FROM Contact WHERE name LIKE  :name1 ")
    List<contact> getContactsByName(String name1);


}
