package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.mycalculator.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
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

        // =
        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                String input = binding.textViewInput.getText().toString();
                String[] array = input.split(" ");
                float result = 0;
                //binding.textViewShow.setText(String.valueOf(array[2].toString().length()));
                for(int i = 1 ; i < array.length ; i+=2)
                {
                    //binding.textViewShow.setText(String.valueOf(binding.buttonAddition.getText().toString() == array[i]));
                    if(array[i].equals(binding.buttonAddition.getText().toString()))
                    {
                        result += Float.parseFloat(array[i - 1]) + Float.parseFloat(array[i + 1]);
                    }
                    else if( array[i].equals(binding.buttonSubtraction.getText().toString()))
                    {
                        result += Float.parseFloat(array[i - 1]) - Float.parseFloat(array[i + 1]);
                    }
                    else if(array[i].equals(binding.buttonMultiplication.getText().toString()))
                    {
                        result += Float.parseFloat(array[i - 1]) * Float.parseFloat(array[i + 1]);
                    }
                    else if(array[i].equals(binding.buttonDivision.getText().toString()))
                    {
                        result += Float.parseFloat(array[i - 1]) / Float.parseFloat(array[i + 1]);
                    }
                    else
                    {
                        count = 1;
                        //binding.textViewShow.setText("Lỗi cú pháp");
                    }
                }

                if(count == 1)
                {
                    binding.textViewShow.setText("Lỗi cú pháp");
                }
                else
                {
                    binding.textViewShow.setText(String.valueOf(result));
                    arrayList.add(binding.textViewInput.getText().toString() + " = " + result);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
        // Clear
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textViewShow.setText("0");
                binding.textViewInput.setText("");
            }
        });

        binding.buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + " " + binding.buttonAddition.getText().toString() + " ");
            }
        });

        binding.buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + " " + binding.buttonSubtraction.getText().toString() + " ");
            }
        });

        binding.buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + " " + binding.buttonMultiplication.getText().toString() + " ");
            }
        });

        binding.buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + " " + binding.buttonDivision.getText().toString() + " ");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonZero.getText().toString());
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonOne.getText().toString());
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonTwo.getText().toString());
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonThree.getText().toString());
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonFour.getText().toString());
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonFive.getText().toString());
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonSix.getText().toString());
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonSeven.getText().toString());
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonEight.getText().toString());
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonNine.getText().toString());
            }
        });

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textCurrent = binding.textViewInput.getText().toString();
                binding.textViewInput.setText(textCurrent + binding.buttonDot.getText().toString());
            }
        });

    }
}