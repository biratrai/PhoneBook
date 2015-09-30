package com.gooner10.contactsprovider;

public class ContactModel {
    String mNumber;
    String mDeleted;
    String mLastContact;

    public ContactModel(String mNumber, String mDeleted, String mLastContact) {
        this.mNumber = mNumber;
        this.mDeleted = mDeleted;
        this.mLastContact = mLastContact;
    }

    public String getmNumber() {
        return mNumber;
    }

    public String getmDeleted() {
        return mDeleted;
    }

    public String getmLastContact() {
        return mLastContact;
    }
}
