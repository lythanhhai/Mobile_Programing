package com.example.dogapp.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.Model.DogBreed;
import com.example.dogapp.Model.DogsApi;
import com.example.dogapp.R;
import com.example.dogapp.ViewModel.DogsAdapter;
import com.example.dogapp.ViewModel.DogsApiService;
import com.example.dogapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class listFragment extends Fragment {

    private DogsApi api;
    private List<DogBreed> dogs = new ArrayList<>();
    private DogsAdapter dogsAdapter;
    private RecyclerView rvDogs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDogs = view.findViewById(R.id.rv_dogs);
        //dogs = new ArrayList<DogBreed>();
        dogsAdapter = new DogsAdapter(dogs);
        rvDogs.setAdapter(dogsAdapter);
        rvDogs.setLayoutManager(new GridLayoutManager(getContext(), 2));

        api = DogsApiService.getApi();
        api.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
//                        dogs.clear();
//                        dogs.addAll(dogBreeds);
//                        dogsAdapter.notifyDataSetChanged();
                        for(DogBreed dog: dogBreeds)
                        {
                            Log.d("DEBUG1", dog.getName());
                            dogBreeds.add(dog);
                            dogsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG", e.getMessage());
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


}