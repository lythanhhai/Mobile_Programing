package com.example.dogapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.dogapp.Model.DogBreed;
import com.example.dogapp.Model.DogsApi;
import com.example.dogapp.ViewModel.DogsAdapter;
import com.example.dogapp.ViewModel.DogsApiService;
import com.example.dogapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
//    private DogsApiService apiService;
//    private List<DogBreed> dogs;
//    private DogsAdapter dogsAdapter;
    private DogsApi api;
    private List<DogBreed> dogs = new ArrayList<>();
    private DogsAdapter dogsAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

//        dogsAdapter = new DogsAdapter(dogs);
//        binding.rvDogs.setAdapter(dogsAdapter);
//        binding.rvDogs.setLayoutManager(new GridLayoutManager(this, 2));
//
//        apiService = new DogsApiService();
//        apiService.getDogs()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
//                    @Override
//                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
//                        dogs.clear();
//                        dogs.addAll(dogBreeds);
//                        dogsAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d("DEBUG", e.getMessage());
//                    }
//                });
        dogsAdapter = new DogsAdapter(dogs);
        binding.rvDogs.setAdapter(dogsAdapter);
        binding.rvDogs.setLayoutManager(new GridLayoutManager(this, 2));

        api = DogsApiService.getApi();
        api.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        dogs.clear();
                        dogs.addAll(dogBreeds);
                        dogsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG", e.getMessage());
                    }
                });
    }
}