package com.example.contactapp1911;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.contactapp1911.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contacts;
    private ContactAdapter contactsAdapter;
    private ContactAdapter contactsAdapterSearch;
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
        contacts = new ArrayList<Contact>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.nullTable();
                Contact user1 = new Contact("Nguyen Van A", "1234565", "nva@gmail.com");
                Contact user2 = new Contact("Nguyen Van B", "5345345", "nvb@gmail.com");
                Contact user3 = new Contact("Nguyen Van C", "6235435", "nvc@gmail.com");
                contactDao.insertAll(user1);
                contactDao.insertAll(user2);
                contactDao.insertAll(user3);
                List<Contact> list = contactDao.getAllContacts();
                //list.clear();
                for(int i = 0; i < list.size(); i++)
                {
                    contacts.add(list.get(i));
                }
            }
        });

//        contacts.add(new Contact("Nguyen Van A", "1234565", "nva@gmail.com"));
//        contacts.add(new Contact("Nguyen Van B", "5345345", "nvb@gmail.com"));
//        contacts.add(new Contact("Nguyen Van C", "6235435", "nvc@gmail.com"));

        contactsAdapter = new ContactAdapter(contacts);
        binding.rvContact.setAdapter(contactsAdapter);
        binding.rvContact.setLayoutManager(new LinearLayoutManager(this));

        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.nav_menu, menu);

            MenuItem menuItem = menu.findItem(R.id.nav_search);
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setQueryHint("Search Contact");

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    contactsAdapterSearch = new ContactAdapter(contactsAdapter.getItemByName(newText));
                    binding.rvContact.setAdapter(contactsAdapterSearch);
                    return false;
                }
            });
            return super.onCreateOptionsMenu(menu);
        }
}