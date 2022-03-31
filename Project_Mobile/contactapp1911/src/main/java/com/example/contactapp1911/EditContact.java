package com.example.contactapp1911;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityAddContactBinding;
import com.example.contactapp1911.databinding.ActivityEditContactBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class EditContact extends AppCompatActivity {

    ActivityEditContactBinding binding;
    String name = "";
    String phone = "";
    String email = "";
    String url = "";
    int id = -1;
    private AppDatabase appDatabase;
    private ContactDao contactDao;

    public byte[] getBytesArrayFromURI(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }

            return byteBuffer.toByteArray();

        }catch(Exception e) {
            Log.d("exception", "Oops! Something went wrong.");
        }
        return null;
    }

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
            id = Integer.parseInt(intent.getStringExtra("id"));
            url = intent.getStringExtra("url");
            binding.etName.setText(name);
            binding.etPhone.setText(phone);
            binding.etEmail.setText(email);
            Bitmap bitmap = BitmapFactory.decodeByteArray(getBytesArrayFromURI(Uri.parse(url)) , 0, getBytesArrayFromURI(Uri.parse(url)).length);
            binding.imAvatar.setImageBitmap(bitmap);
        }
        // chọn ảnh
        binding.btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                name = binding.etName.getText().toString();
                phone = binding.etPhone.getText().toString();
                email = binding.etEmail.getText().toString();
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("url", url);
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        //Contact user = new Contact(name, phone, email);
                        //contactDao.updateContact(user);
                        contactDao.updateContactByID(id, name, phone, email);
                    }
                });
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
                url = selectedImage.toString();
                binding.imAvatar.setImageURI(selectedImage);
            }
        }
    }
}