package com.example.contactapp1911;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.contactapp1911.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact1> contacts;
    private ContactAdapter contactsAdapter;
    private ContactAdapter contactsAdapterSearch;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    ActivityResultLauncher<Intent> getInfoNewContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();
        contacts = new ArrayList<Contact1>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.nullTable();
                Contact1 user1 = new Contact1("Nguyen Van A", "0945443534", "nva@gmail.com", "");
                Contact1 user2 = new Contact1("Ho Van B", "0845443454", "hvb@gmail.com", "");
                Contact1 user3 = new Contact1("Tran Van C", "0545465534", "tvc@gmail.com", "");
                contactDao.insertAll(user1);
                contactDao.insertAll(user2);
                contactDao.insertAll(user3);
                List<Contact1> list = contactDao.getAllContacts();
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
        // l???y th??ng tin add
        getInfoNewContact = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK && result != null) {
                            if(result.getData() != null && result.getData().getStringExtra("name") != null && result.getData().getStringExtra("phone") != null && result.getData().getStringExtra("email") != null)
                            {
                                Intent data = result.getData();
                                // your operation....
                                String name = data.getStringExtra("name");
                                String phone = data.getStringExtra("phone");
                                String email = data.getStringExtra("email");
                                String url = data.getStringExtra("url");
                                // S??? d???ng k???t qu??? result b???ng c??ch hi???n Toast
                                binding.textView.setText("");
                                //binding.rvContact.setAdapter(contactsAdapter);
                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                            //contactDao.nullTable();
                                            Contact1 userContact = new Contact1(name, phone, email, url);
                                            contactDao.insertContact(userContact);
                                            contacts.clear();
                                            List<Contact1> list = contactDao.getAllContacts();
                                            for(int i = 0; i < list.size(); i++)
                                            {
                                                contacts.add(list.get(i));
                                            }
                                            binding.rvContact.setAdapter(contactsAdapter);
                                        }
                                });
                            }
                        }
                    }
                });


        // add
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Contact.class);
                getInfoNewContact.launch(intent);
            }
        });

        // clcik item -> detail contact
        binding.rvContact.addOnItemTouchListener(new RecycleItemListener(getApplicationContext(), binding.rvContact, new RecycleItemListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(MainActivity.this, DetailContact.class);
                Contact1 user = contacts.get(position);
                intent.putExtra("name", user.getName());
                intent.putExtra("phone", user.getMobile());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("id", String.valueOf(user.getId()));
                intent.putExtra("url", user.getUrl().toString());
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("object_contact", contacts.get(position));
//                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

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