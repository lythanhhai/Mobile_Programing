package com.example.dogapp.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.Model.DogBreed;
import com.example.dogapp.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;


public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder>{
    private List<DogBreed> dogs;

    public DogsAdapter(List<DogBreed> dogs) {
        this.dogs = dogs;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // normal
        private final ImageView image;
        private final TextView name, description;
        // swipe
        private final TextView origin_value, life_value, name_swipe, description_value;


        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            origin_value = (TextView) view.findViewById(R.id.origin_value);
            life_value = (TextView) view.findViewById(R.id.life_value);
            name_swipe = (TextView) view.findViewById(R.id.name_swipe);
            description_value = (TextView) view.findViewById(R.id.description_value);
        }

        public ImageView getImage() {
            return image;
        }
        public TextView getName() { return name; }
        public TextView getDescription() { return description; }
        public TextView getName1() { return name_swipe; }
        public TextView getDescription1() { return description_value; }
        public TextView getOrigin() { return origin_value; }
        public TextView getLife() { return life_value; }
    }

    @NonNull
    @Override
    public DogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_dog_item, parent, false);

        return new ViewHolder(view);
    }

    public List<DogBreed> getItemByName(String text)
    {
        List<DogBreed> list = new ArrayList<DogBreed>();
        for(int i = 0; i < dogs.size(); i++)
        {
            if(dogs.get(i).getName().contains(text))
            {
                list.add(dogs.get(i));
            }
        }
        return list;
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {
        holder.getName().setText(dogs.get(position).getName());
        holder.getDescription().setText(dogs.get(position).getBredFor());
        Picasso.get()
                .load(dogs.get(position).getUrl())
                .placeholder(R.drawable.ic_baseline_image_24)
                .fit()
                .into(holder.getImage());
        holder.getName1().setText(dogs.get(position).getName());
        holder.getDescription1().setText(dogs.get(position).getBredFor());
        holder.getOrigin().setText(dogs.get(position).getOrigin());
        holder.getLife().setText(dogs.get(position).getLifeSpan());
    }

    @Override
    public int getItemCount()
    {
        return dogs.size();
    }


}
