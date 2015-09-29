package com.gooner10.contactsprovider;

public class ContactModel {
    String mNumber;
    boolean mDeleted;
    String mLastContact;

    public ContactModel(String mNumber, boolean mDeleted, String mLastContact) {
        this.mNumber = mNumber;
        this.mDeleted = mDeleted;
        this.mLastContact = mLastContact;
    }
}
