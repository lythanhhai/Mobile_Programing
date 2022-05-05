package com.midterm.contacapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.midterm.contacapp.databinding.AddUserBinding;
public class add extends AppCompatActivity {
    AddUserBinding binding;
    private  String name,phone,email;

    private  ContactAdapter contactsAdapter;
    private AppDatabase appDatabase;
    private contactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= AddUserBinding.inflate(getLayoutInflater());
        View viewRoot= binding.getRoot();
        setContentView(viewRoot);

         appDatabase= AppDatabase.getInstance(this);
        contactDAO = appDatabase.contactDAO();
        Intent intent = getIntent();
        if (intent != null){
            
        }
        binding.bnAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            name=binding.eFn.getText().toString().trim()+binding.eLn.getText().toString().trim();
            phone=binding.ePhone.toString().trim();
            email=binding.eMail.toString().trim();
            if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(email))
            {
             return;
            }
                contact a = new contact(name,phone,email);
                contactDAO.insertAll(a);
                Intent intent = new Intent(add.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}