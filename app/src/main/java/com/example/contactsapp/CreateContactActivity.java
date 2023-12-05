package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateContactActivity extends AppCompatActivity {

    private EditText name, phoneNumber, email, address, birthday, note;
    private RadioGroup genderRadioGroup;
    private ImageView saveProfile, profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        name = findViewById(R.id.Text_Name);
        phoneNumber = findViewById(R.id.Text_Phone);
        email = findViewById(R.id.Text_Email);
        address = findViewById(R.id.Text_Address);
        birthday = findViewById(R.id.Input_Birthday);
        note = findViewById(R.id.Text_Note);
        saveProfile = findViewById(R.id.save_Profile);
        genderRadioGroup = findViewById(R.id.radioBtnGender);

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsDatabaseHelper myDB = new ContactsDatabaseHelper(CreateContactActivity.this);
                String contactName = name.getText().toString();
                String contactPhoneNumber = phoneNumber.getText().toString();
                String contactEmail = email.getText().toString();
                String contactAddress = address.getText().toString();
                String contactNote = note.getText().toString();
                String contactBirthday = birthday.getText().toString();

                if (TextUtils.isEmpty(contactName) || TextUtils.isEmpty(contactPhoneNumber) || TextUtils.isEmpty(contactEmail)) {
                    Toast.makeText(CreateContactActivity.this, "Please enter name, phone number, and email", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Date birthdayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(contactBirthday);

                        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
                        String contactGender = selectedGenderRadioButton.getText().toString();

                        Contacts newContact = new Contacts(0, contactName, R.drawable.contact, contactPhoneNumber, contactEmail, contactAddress, contactGender, birthdayDate, contactNote);
                        myDB.addContact(newContact);
                        Toast.makeText(CreateContactActivity.this, "Contact added successfully", Toast.LENGTH_SHORT).show();

                        // Revenir à MainActivity
                        Intent intent = new Intent(CreateContactActivity.this, MainActivity.class);
                        startActivity(intent);

                        // Fermer l'activity actuelle
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(CreateContactActivity.this, "Invalid birthday format", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
