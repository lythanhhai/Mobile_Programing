package com.example.contactapp1911;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityAddContactBinding;
import com.example.contactapp1911.databinding.ActivityDetailContactBinding;

public class DetailContact extends AppCompatActivity {

    ActivityDetailContactBinding binding;
    String name = "";
    String phone = "";
    String email = "";
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

                binding.tvName.setText(name1);
                binding.tvPhone.setText(phone1);
                binding.tvEmail.setText(email1);
                name = binding.tvName.getText().toString();
                phone = binding.tvPhone.getText().toString();
                email = binding.tvEmail.getText().toString();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);



        Intent intent = getIntent();
        if(intent != null)
        {
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            email = intent.getStringExtra("email");
            id = Integer.parseInt(intent.getStringExtra("id"));
            binding.tvName.setText(name);
            binding.tvPhone.setText(phone);
            binding.tvEmail.setText(email);
        }

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailContact.this, EditContact.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("id", String.valueOf(id));
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