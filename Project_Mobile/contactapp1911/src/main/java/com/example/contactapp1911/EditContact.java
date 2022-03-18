package com.example.contactapp1911;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp1911.databinding.ActivityAddContactBinding;
import com.example.contactapp1911.databinding.ActivityEditContactBinding;

public class EditContact extends AppCompatActivity {

    ActivityEditContactBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Intent intent = getIntent();
        if(intent != null)
        {

        }

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", binding.etName.getText().toString());
                intent.putExtra("phone", binding.etPhone.getText().toString());
                intent.putExtra("email", binding.etEmail.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}