package com.example.contactsapp;

import java.util.Date;

public class Contacts {

    public Contacts() {
        this.idContact = 0;
        this.name = null;
        this.image = 0;
        this.phoneNumber = null;
        this.email = null;
        this.address = null;
        this.gender = null;
        this.birthday = null;
        this.note = null;
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    private int idContact;
    private String name;

    private int image;

    private String phoneNumber;

    private  String email;

    private String address;

    private String gender;

    private Date birthday;

    private String note;


    public Contacts(int idContact, String name, int image, String phoneNumber, String email, String address, String gender, Date birthday, String note) {
        this.idContact = idContact;
        this.name = name;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.note = note;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
