package com.example.dogapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp1911.RecycleItemListener;
import com.example.dogapp.Model.DogBreed;
import com.example.dogapp.Model.DogsApi;
import com.example.dogapp.ViewModel.DogsAdapter;
import com.example.dogapp.ViewModel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class listFragment extends Fragment {

    private DogsApi api;
    private List<DogBreed> dogs = new ArrayList<>();
    private DogsAdapter dogsAdapter;
    private DogsAdapter dogsAdapterSearch;
    private RecyclerView rvDogs;
    private LinearLayout normal, swipe;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {

        }

        rvDogs.addOnItemTouchListener(new RecycleItemListener(getContext(), rvDogs, new RecycleItemListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setClass(MainActivity.this, DetailContact.class);
//                Contact1 user = contacts.get(position);
//                intent.putExtra("name", user.getName());
//                intent.putExtra("phone", user.getMobile());
//                intent.putExtra("email", user.getEmail());
//                intent.putExtra("id", String.valueOf(user.getId()));
//                intent.putExtra("url", user.getUrl().toString());
//                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    // menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Contact");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dogsAdapterSearch = new DogsAdapter(dogsAdapter.getItemByName(newText));
                rvDogs.setAdapter(dogsAdapterSearch);
                //binding.fragmentContainerView
                return false;
            }
        });
        //return super.onCreateOptionsMenu(menu, inflater);
    }

    private class SwipeListener implements View.OnTouchListener
    {
        GestureDetector gestureDetector;
        SwipeListener(View view)
        {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener()
                    {
                        @Override
                        public boolean onDown(MotionEvent e) {
                            return true;
                        }

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();
                            try {
                                if(Math.abs(xDiff) > Math.abs(yDiff))
                                {
                                    if(Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold)
                                    {
                                        if(xDiff > 0)
                                        {

                                        }
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    };
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDogs = view.findViewById(R.id.rv_dogs);
        normal = view.findViewById(R.id.normal);
        swipe = view.findViewById(R.id.swipe);
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
                        dogs.clear();
                        dogs.addAll(dogBreeds);
                        dogsAdapter.notifyDataSetChanged();
//                        for(DogBreed dog: dogBreeds)
//                        {
//                            Log.d("DEBUG1", dog.getName());
//                            dogBreeds.add(dog);
//                            dogsAdapter.notifyDataSetChanged();
//                        }
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