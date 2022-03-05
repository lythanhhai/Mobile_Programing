package com.example.helloworld;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.helloworld.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

//    private TextView tvCount;
//    private FloatingActionButton btnUp;
//    private FloatingActionButton btnDown;
    // lấy kết quả trả về
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    private ActivityMainBinding binding;
    private MyViewModel model;
    private UpdateListModel listModel;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    ActivityResultLauncher<Intent> takeNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.lvCount.setAdapter(arrayAdapter);


        model = new ViewModelProvider(this).get(MyViewModel.class);
        listModel = new ViewModelProvider(this).get(UpdateListModel.class);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
                arrayList.add("" + integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listModel.getNumber().observe(this, new Observer<ArrayList<String>>() {
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

        takeNumber = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK && result != null) {
                            if(result.getData() != null && result.getData().getStringExtra("numberInEdit") != null && result.getData().getStringExtra("indexToEdit") != null)
                            {
                                Intent data = result.getData();
                                // your operation....
                                String Res = data.getStringExtra("numberInEdit");
                                String index = data.getStringExtra("indexToEdit");
                                // Sử dụng kết quả result bằng cách hiện Toast
                                arrayList.set(Integer.parseInt(index), Res);
                                //binding.tvCount.setText(result.getData().getStringExtra("numberInEdit").toString());
                                arrayAdapter.notifyDataSetChanged();
                            }
                                //binding.tvCount.setText(result.getData().toString());
                        }
                    }
                });

        binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  model.increaseNumber();
                  int current = Integer.parseInt(binding.tvCount.getText().toString());
                  listModel.addToList(String.valueOf(current));
            }
        });

        binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(binding.tvCount.getText().toString());
                binding.tvCount.setText("" + --count);
            }
        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayList.remove(i);
                // update UI
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                  intent.putExtra("number", arrayList.get(i));
                  intent.putExtra("index", String.valueOf(i));
                  takeNumber.launch(intent);
                  //takeNumber.launch(intent);
                  //startActivityForResult(intent, REQUEST_CODE_EXAMPLE);

            }
        });



    }

    // Khi kết quả được trả về từ Activity khác, hàm onActivityResult sẽ được gọi.
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
//        if(requestCode == REQUEST_CODE_EXAMPLE) {
//
//            // resultCode được set bởi DetailActivity
//            // RESULT_OK chỉ ra rằng kết quả này đã thành công
//            if(resultCode == Activity.RESULT_OK) {
//                // Nhận dữ liệu từ Intent trả về
//                String result = data.getStringExtra("numberInEdit");
//                String index = data.getStringExtra("indexToEdit");
//                // Sử dụng kết quả result bằng cách hiện Toast
//                arrayList.set(Integer.parseInt(index), result);
//                binding.tvCount.setText(index);
//                arrayAdapter.notifyDataSetChanged();
//            } else {
//                // DetailActivity không thành công, không có data trả về.
//
//            }
//        }
//    }
}