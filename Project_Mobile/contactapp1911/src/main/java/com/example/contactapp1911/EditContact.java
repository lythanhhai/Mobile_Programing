package com.example.contactapp1911;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityAddContactBinding;
import com.example.contactapp1911.databinding.ActivityEditContactBinding;

public class EditContact extends AppCompatActivity {

    ActivityEditContactBinding binding;
    String name = "";
    String phone = "";
    String email = "";
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();

        Intent intent = getIntent();
        if(intent != null)
        {
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            email = intent.getStringExtra("email");
            binding.etName.setText(name);
            binding.etPhone.setText(phone);
            binding.etEmail.setText(email);
        }

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                name = binding.etName.getText().toString();
//                phone = binding.etPhone.getText().toString();
//                email = binding.etEmail.getText().toString();
//                intent.putExtra("name1", name);
//                intent.putExtra("phone1", phone);
//                intent.putExtra("email1", email);
//                Contact user = new Contact(name, phone, email);
//                contactDao.updateContact(user);
//                setResult(1, intent);
//                //RESULT_OK
//                finish();
                Intent intent = new Intent();
                name = binding.etName.getText().toString();
                phone = binding.etPhone.getText().toString();
                email = binding.etEmail.getText().toString();
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                //Contact user = new Contact(name, phone, email);
                //contactDao.updateContact(user);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}