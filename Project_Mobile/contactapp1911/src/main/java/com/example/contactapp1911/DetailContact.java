package com.example.contactapp1911;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.contactapp1911.databinding.ActivityAddContactBinding;
import com.example.contactapp1911.databinding.ActivityDetailContactBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DetailContact extends AppCompatActivity {

    ActivityDetailContactBinding binding;
    String name = "";
    String phone = "";
    String email = "";
    String url = "";
    Uri uri;
    int id = -1;
    ActivityResultLauncher<Intent> getInfoNewContactEdit;

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String name1 = data.getStringExtra("name");
                String phone1 = data.getStringExtra("phone");
                String email1 = data.getStringExtra("email");
                String url1 = data.getStringExtra("url");

                binding.tvName.setText(name1);
                binding.tvPhone.setText(phone1);
                binding.tvEmail.setText(email1);
                binding.imAvatar.setImageURI(Uri.parse(url1));
                name = binding.tvName.getText().toString();
                phone = binding.tvPhone.getText().toString();
                email = binding.tvEmail.getText().toString();
                url = url1;
            }
        }
    }

    public void getPathFromURI(Uri contentUri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        binding.imAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);



        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

        if(intent != null)
        {
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            email = intent.getStringExtra("email");
            id = Integer.parseInt(intent.getStringExtra("id"));
            url = intent.getStringExtra("url");
            //uri = Uri.parse(url);
            //Contact1 contact = (Contact1) bundle.get("object_contact");
            binding.tvName.setText(name);
            binding.tvPhone.setText(phone);
            binding.tvEmail.setText(email);
            //File file = new File(Uri.parse(url));

//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getContentResolver().query(Uri.parse(contact.getUrl()),
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//            binding.imAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //Bitmap map = BitmapFactory.decodeFile(url);

//            Bitmap map = null;
//            try {
//                map = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(url));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            binding.imAvatar.setImageBitmap(map);
            //binding.imAvatar.setImageURI(Uri.parse(url));

//            if (extras != null && extras.containsKey("url")) {
//                url= extras.getString("url");
//            }
//            binding.imAvatar.setImageURI(Uri.parse(url));

        }


        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailContact.this, EditContact.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("url", url);
                startActivityForResult(intent, 1);
            }
        });


        // edit
//        getInfoNewContactEdit = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == 1 && result != null) {
//                            if(result.getData() != null && result.getData().getStringExtra("name1") != null && result.getData().getStringExtra("phone1") != null && result.getData().getStringExtra("email1") != null)
//                            {
//                                Intent data = result.getData();
//                                // your operation....
//                                String name1 = data.getStringExtra("name1");
//                                String phone1 = data.getStringExtra("phone1");
//                                String email1 = data.getStringExtra("email1");
//
//                                binding.tvName.setText(name1);
//                                binding.tvPhone.setText(phone1);
//                                binding.tvEmail.setText(email1);
//                                // Sử dụng kết quả result bằng cách hiện Toast
//                                //binding.textView.setText("");
//                            }
//                            else
//                            {
//                            }
//                        }
//                        else
//                        {
//                            binding.tvName.setText(String.valueOf(result.getData()));
//                        }
//                    }
//                });
//
//        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DetailContact.this, EditContact.class);
//                intent.putExtra("name", name);
//                intent.putExtra("phone", phone);
//                intent.putExtra("email", email);
//                getInfoNewContactEdit.launch(intent);
//            }
//        });
//        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("name", binding.etName.getText().toString());
//                intent.putExtra("phone", binding.etPhone.getText().toString());
//                intent.putExtra("email", binding.etEmail.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
    }
}