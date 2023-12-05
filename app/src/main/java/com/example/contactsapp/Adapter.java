package com.example.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Contacts> {
    public Adapter(@NonNull Context context,  List<Contacts> contactsList) {
        super(context, 0, contactsList);
    }
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Contacts currentContact = getItem(position);

        ImageView imageViewContact = convertView.findViewById(R.id.imageViewContact);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhoneNumber = convertView.findViewById(R.id.textViewPhoneNumber);

        if (currentContact != null) {
            imageViewContact.setImageResource(currentContact.getImage());
            textViewName.setText(currentContact.getName());
            textViewPhoneNumber.setText(currentContact.getPhoneNumber());
        }

        return convertView;
    }

}
