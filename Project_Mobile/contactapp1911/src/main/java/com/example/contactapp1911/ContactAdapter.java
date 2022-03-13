package com.example.contactapp1911;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
{
    private ArrayList<Contact> Contacts;

    public ContactAdapter(ArrayList<Contact> contacts)
    {
        this.Contacts = contacts;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_contact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(Contacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView iv_avatar;

        public ViewHolder(View view) {
            super(view);

            tv_name = view.findViewById(R.id.tv_name);
            iv_avatar = view.findViewById(R.id.iv_avatar);
        }
    }
}