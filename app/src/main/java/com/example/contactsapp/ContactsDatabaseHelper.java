package com.example.contactsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    // Contacts table
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_NOTE = "note";

    // SQL statement to create the contacts table
    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_IMAGE + " INTEGER," +
            COLUMN_PHONE_NUMBER + " TEXT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_ADDRESS + " TEXT," +
            COLUMN_GENDER + " TEXT," +
            COLUMN_BIRTHDAY + " TEXT," +
            COLUMN_NOTE + " TEXT)";


    public ContactsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    public void addContact(Contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_IMAGE, contact.getImage());
        values.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_ADDRESS, contact.getAddress());
        values.put(COLUMN_GENDER, contact.getGender());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(contact.getBirthday());
        values.put(COLUMN_BIRTHDAY, formattedDate);

        values.put(COLUMN_NOTE, contact.getNote());

        long id = db.insert(TABLE_CONTACTS, null, values);
        db.close();
        if(id == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    // Méthode pour récupérer tous les contacts de la base de données
    @SuppressLint("Range")
    public List<Contacts> getAllContacts()  {
        List<Contacts> contactList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contacts contact = new Contacts();
                contact.setIdContact(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                contact.setImage(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)));
                contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                contact.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                contact.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));

                String dateString = cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDAY));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                try {
                    contact.setBirthday(dateFormat.parse(dateString));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                contact.setNote(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contactList;
    }
}
