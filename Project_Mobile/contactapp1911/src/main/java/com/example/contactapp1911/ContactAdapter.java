package com.example.contactapp1911;

import android.net.Uri;
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
    private ArrayList<Contact1> Contacts;

    public ContactAdapter(ArrayList<Contact1> contacts)
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
        if(String.valueOf(Contacts.get(position).getUrl() == "") == "0")
        {
            holder.iv_avatar.setImageResource(R.drawable.ic_baseline_person_24);
        }
        else
        {
            holder.iv_avatar.setImageURI(Uri.parse(Contacts.get(position).getUrl()));

        }
    }

    @Override
    public int getItemCount() {
        return Contacts.size();
    }

    public ArrayList<Contact1> getItemByName(String name) {
        ArrayList<Contact1> contactSearch = new ArrayList<Contact1>();
        for(int i = 0; i < Contacts.size(); i++)
        {
            if(Contacts.get(i).getName().contains(name))
            {
                contactSearch.add(Contacts.get(i));
            }
        }
        return contactSearch;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView iv_avatar;

        public ViewHolder(View view) {
            super(view);

            tv_name = view.findViewById(R.id.tv_name);
            iv_avatar = view.findViewById(R.id.avatar);
        }
    }
}