package com.example.contactapp1911;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contacts;
    private ContactAdapter contactsAdapter;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Contact user1 = new Contact("Nguyen Van A", "1234565", "nva@gmail.com");
                contactDao.insertAll(user1);
            }
        });


        contacts = new ArrayList<Contact>();
//        contacts.add(new Contact("Nguyen Van A", "1234565", "nva@gmail.com"));
//        contacts.add(new Contact("Nguyen Van B", "5345345", "nvb@gmail.com"));
//        contacts.add(new Contact("Nguyen Van C", "6235435", "nvc@gmail.com"));

        contactsAdapter = new ContactAdapter(contacts);
        binding.rvContact.setAdapter(contactsAdapter);
        binding.rvContact.setLayoutManager(new LinearLayoutManager(this));

        }
}