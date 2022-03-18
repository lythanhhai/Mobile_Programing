package com.midterm.contacapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.contacapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<contact> contacts;
    private ActivityMainBinding binding;

    public ContactAdapter(List<contact> contacts)
    {
        this.contacts=contacts;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      contact con= contacts.get(position);
      holder.tv_name.setText(con.getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ImageView ivAvatar;


        public ViewHolder(View view) {
            super(view);
            tv_name=view.findViewById(R.id.tv_name);
            ivAvatar=view.findViewById(R.id.id_img);



        }


    }
}
