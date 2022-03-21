package com.example.contactapp1911;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityAddContactBinding;
import com.example.contactapp1911.databinding.ActivityMainBinding;

public class Add_Contact extends AppCompatActivity {

    ActivityAddContactBinding binding;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();

        Intent intent = getIntent();
        if(intent != null)
        {
        }


        // chọn ảnh
        binding.btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Contact userContact = new Contact(binding.etName.getText().toString(), binding.etPhone.getText().toString(), binding.etEmail.getText().toString());
//                contactDao.insertContact(userContact);
                Intent intent = new Intent();
                intent.putExtra("name", binding.etName.getText().toString());
                intent.putExtra("phone", binding.etPhone.getText().toString());
                intent.putExtra("email", binding.etEmail.getText().toString());
                intent.putExtra("url", uri.toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null)
        {
            if(resultCode == RESULT_OK)
            {
                Uri selectedImage = data.getData();
                uri = selectedImage;
                binding.imAvatar.setImageURI(selectedImage);
            }
        }
    }

}