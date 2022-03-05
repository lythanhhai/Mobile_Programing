package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.twonumbers.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    private UpdateModel historyModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.lvHistory.setAdapter(arrayAdapter);

        historyModel = new ViewModelProvider(this).get(UpdateModel.class);

        historyModel.getHistory().observe(this,new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> list) {

                arrayList.clear();
                for(int i = 0; i < list.size(); i++)
                {
                    arrayList.add(list.get(i));
                }

                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                float number1 = Float.parseFloat(binding.etA.getText().toString());
                float number2 = Float.parseFloat(binding.etB.getText().toString());
                result = String.valueOf(number1 + number2);
                String str = String.valueOf(number1)  + " + " + String.valueOf(number2) + " = " + result;
                arrayList.add(str);
                historyModel.addToList(str);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                float number1 = Float.parseFloat(binding.etA.getText().toString());
                float number2 = Float.parseFloat(binding.etB.getText().toString());
                result = String.valueOf(number1 - number2);
                String str = String.valueOf(number1)  + " - " + String.valueOf(number2) + " = " + result;
                arrayList.add(str);
                historyModel.addToList(str);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                float number1 = Float.parseFloat(binding.etA.getText().toString());
                float number2 = Float.parseFloat(binding.etB.getText().toString());
                result = String.valueOf(number1 * number2);
                String str = String.valueOf(number1)  + " * " + String.valueOf(number2) + " = " + result;
                arrayList.add(str);
                historyModel.addToList(str);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        binding.btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                float number1 = Float.parseFloat(binding.etA.getText().toString());
                float number2 = Float.parseFloat(binding.etB.getText().toString());
                result = String.valueOf(number1 / number2);
                String str = String.valueOf(number1)  + " / " + String.valueOf(number2) + " = " + result;
                arrayList.add(str);
                historyModel.addToList(str);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}