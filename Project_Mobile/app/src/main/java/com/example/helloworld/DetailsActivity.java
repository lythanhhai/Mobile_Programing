package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.databinding.ActivityDetailsBinding;
import com.example.helloworld.databinding.ActivityMainBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Intent intent = getIntent();
        if(intent != null)
        {
            String data = intent.getStringExtra("number");
            binding.tvDetail.setText(data);
        }

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent.putExtra("numberInEdit", binding.etDetails.getText());
                Intent intent = new Intent();
                intent.putExtra("numberInEdit", binding.etDetails.getText());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
