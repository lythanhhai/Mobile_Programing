package com.midterm.contacapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.midterm.contacapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private contactDAO contactDAO;
    private static final int REQ_CODE = 123;
    private ActivityMainBinding binding;
 private  String name, phone, email;
    private ContactAdapter cont;

private  AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        appDatabase = AppDatabase.getInstance(this);
        contactDAO = appDatabase.contactDAO();
        List<contact> contacts = contactDAO.getAllContacts();
        cont = new ContactAdapter(contacts);
        binding.rvContacts.setAdapter(cont);

        binding.button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,add.class);
                startActivity(intent);
            }
        });
        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = binding.edSearch.getText().toString();
                if (name !="")
                {
                    List<contact> contacts = contactDAO.getContactsByName('%'+name+'%');
                    cont = new ContactAdapter(contacts);
                    binding.rvContacts.setAdapter(cont);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });


        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add.class);

                startActivity(intent);
            }
        });



    }
}
