package com.example.contactapp1911;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contacts;
    private CustomAdapter contactsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        contacts = new ArrayList<Contact>();
        contacts.add(new Contact(1, "Nguyen Van A", "123456", "nva@gmail.com"));
        contacts.add(new Contact(2, "Nguyen Van B", "123456", "nvb@gmail.com"));
        contacts.add(new Contact(3, "Nguyen Van C", "123456", "nvc@gmail.com"));

//        contactsAdapter = new CustomAdapter(contacts);
//        binding.rvContact.setAdapter(contactsAdapter);
//        binding.rvContact.setLayoutManager();

        }
}