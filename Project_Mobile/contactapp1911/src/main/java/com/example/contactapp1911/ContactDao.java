package com.example.contactapp1911;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * From Contact1")
    List<Contact1> getAllContacts();

    @Insert
    void insertAll(Contact1... contact);

    // xóa tất cả
    @Query("DELETE From Contact1")
    public void nullTable();

    @Query("Select * From Contact1 where name = :name")
    List<Contact1> getContactsByName(String name);

    @Insert
    void insertContact(Contact1 contact);

    @Update
    void updateContact(Contact1 contact);

    @Query("Update Contact1 set name = :name, mobile = :phone, email = :email where id = :id")
    void updateContactByID(int id, String name, String phone, String email);
}
