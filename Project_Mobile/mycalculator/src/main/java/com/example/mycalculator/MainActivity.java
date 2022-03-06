package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

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

//                String[] array = input.split(" ");
//                float result = Float.parseFloat(array[0]);
//
//                for(int i = 1 ; i < array.length ; i+=2)
//                {
//                    //binding.textViewShow.setText(String.valueOf(binding.buttonAddition.getText().toString() == array[i]));
//                    if(array[i].equals(binding.buttonAddition.getText().toString()))
//                    {
//                        result += Float.parseFloat(array[i + 1]);
//                    }
//                    else if( array[i].equals(binding.buttonSubtraction.getText().toString()))
//                    {
//                        result -= Float.parseFloat(array[i + 1]);
//                    }
//                    else if(array[i].equals(binding.buttonMultiplication.getText().toString()))
//                    {
//                        result *= Float.parseFloat(array[i + 1]);
//                    }
//                    else if(array[i].equals(binding.buttonDivision.getText().toString()))
//                    {
//                        result /= Float.parseFloat(array[i + 1]);
//                    }
//                    else
//                    {
//                        count = 1;
//                        //binding.textViewShow.setText("Lỗi cú pháp");
//                    }
//                }
//
//                if(count == 1)
//                {
//                    binding.textViewShow.setText("Lỗi cú pháp");
//                }
//                else
//                {
                    int count = 0;
                    String input = binding.textViewInput.getText().toString();
                    String result = String.valueOf(eval(input));
                    binding.textViewShow.setText(result);
                    binding.textViewInput.setText(binding.textViewInput.getText().toString() + " = " + result);
                    arrayList.add(binding.textViewInput.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
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
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}