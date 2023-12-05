package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contacts> contactsList;
    private Adapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer les données depuis la base de données
        ContactsDatabaseHelper dbHelper = new ContactsDatabaseHelper(this);
        contactsList = dbHelper.getAllContacts();

        // Créer votre adaptateur
        contactsAdapter = new Adapter(this, contactsList);

        // Associer l'adaptateur à votre ListView
        ListView listViewContacts = findViewById(R.id.listView_Contacts);
        listViewContacts.setAdapter(contactsAdapter);

        FloatingActionButton add_contact = findViewById(R.id.Add_contact);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
                startActivity(intent);
            }
        });
    }

}