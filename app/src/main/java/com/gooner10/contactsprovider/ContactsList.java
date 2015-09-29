package com.gooner10.contactsprovider;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import static android.provider.ContactsContract.RawContactsEntity;

public class ContactsList extends AppCompatActivity {

    TextView outputText;
    boolean contact = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        outputText = (TextView) findViewById(R.id.textView1);
        fetchContacts();
    }

    public void fetchContacts() {

//        String phoneNumber = null;
//        String email = null;
//
//        // Contacts Content URI
//        Uri CONTENT_URI = Contacts.CONTENT_URI;
        Uri RAW_CONTENT_URI = RawContacts.CONTENT_URI;
//        Uri PhoneCONTENT_URI = Phone.CONTENT_URI;
//
//
//        String _ID = Contacts._ID;
//        String DISPLAY_NAME = Contacts.DISPLAY_NAME;
//        String HAS_PHONE_NUMBER = Contacts.HAS_PHONE_NUMBER;
//        String LAST_TIME_CONTACTED = Contacts.LAST_TIME_CONTACTED;
//
//
//        String Phone_CONTACT_ID = Phone.CONTACT_ID;
//        String NUMBER = Phone.NUMBER;
//
//        Uri EmailCONTENT_URI = Email.CONTENT_URI;
//        String EmailCONTACT_ID = Email.CONTACT_ID;
//        String DATA = Email.DATA;
//
//        ContentResolver contentResolver = getContentResolver();
//
//        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        ArrayList<String> mContactsId = new ArrayList<>();
        ArrayList<ContactModel> mContactsList = new ArrayList<>();
        ContactModel contactModel = null;

//        // Query ContactsContract.Contacts table
//        if (cursor.getCount() > 0) {
//
//            while (cursor.moveToNext()) {
//
//                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
//                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
//                double last_contacted = Double.parseDouble(cursor.getString(cursor.getColumnIndex(LAST_TIME_CONTACTED)));
//                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
//
//                if (hasPhoneNumber > 0) {
//                    outputText.setText(name);
//                    Log.d("MYCONID", "Name: " + name);
//                    Log.d("MYCONID", "Last Contacted: " + last_contacted);
////                    String rawContactId = cursor.getString(cursor.getColumnIndex(_ID));
//                    Log.d("MYCONID", "Last Contacted ID: " + contact_id);
//                    Log.d("MYCONID", "--------------------------------");
//
//                }
//            }

        // Query ContactsContract.RawContacts table
//        Uri entityUri = Uri.withAppendedPath(RAW_CONTENT_URI, RawContacts.Entity.CONTENT_DIRECTORY);
        Cursor c = getContentResolver().query(RAW_CONTENT_URI,
                new String[]{RawContacts.CONTACT_ID, RawContacts.LAST_TIME_CONTACTED, RawContacts._ID}, null, null, null);
        if (c.getCount() > 0) {
            try {
                while (c.moveToNext()) {
                    String sourceId = c.getString(2);
                    String lastContacted = (c.getString(1));
                    Log.d("MYRAWID", "Contact_ID: " + c.getString(0));
                    Log.d("MYRAWID", "Raw Contact_ID: " + c.getColumnName(2));
                    Log.d("MYRAWID", "_ID: " + c.getString(2));
                    Log.d("MYRAWID", "Raw Deleted: " + c.getString(1));
                    Log.d("MYRAWID", "--------------------------------");
                    outputText.setText(c.getString(0));
                    mContactsId.add(sourceId);

                    Uri rawContactUri = ContentUris.withAppendedId(RawContacts.CONTENT_URI, Long.parseLong(sourceId));
                    Uri entityUri = Uri.withAppendedPath(rawContactUri, RawContacts.Entity.CONTENT_DIRECTORY);
                    Cursor c1 = getContentResolver().query(entityUri,
                            new String[]{
                                    RawContacts.SOURCE_ID,
                                    RawContactsEntity.DATA_ID,
                                    RawContactsEntity.MIMETYPE,
                                    RawContactsEntity.DATA1,
                                    RawContactsEntity.DELETED
                            }, null, null, null);
                    try {
                        while (c1.moveToNext()) {
                            String rawId = c1.getString(0);
                            Log.d("RawContactsEntity", "Raw sourceId: " + rawId);
                            if (!c1.isNull(1)) {
                                String dataid = c1.getString(1);
                                String mimeType = c1.getString(2);
                                String data = c1.getString(3);
                                boolean deleted = Boolean.parseBoolean(c1.getString(4));
                                Log.d("RawContactsEntity", "Raw mimeType: " + dataid);
                                Log.d("RawContactsEntity", "Raw mimeType: " + mimeType);
                                Log.d("RawContactsEntity", "Raw data: " + data);
                                Log.d("RawContactsEntity", "Raw data: " + deleted);
                                contactModel = new ContactModel(data, deleted, lastContacted);
                            }
                        }
                    }finally {
                        c1.close();
                    }
                    mContactsList.add(contactModel);
                }
            } finally {
                c.close();
            }
        }
        Log.d("RawContactsEntity","Array: "+mContactsList);
//        }

//        for (String rawContactId : mContactsId) {
//            Uri rawContactUri = ContentUris.withAppendedId(RawContacts.CONTENT_URI, Long.parseLong(rawContactId));
//            Uri entityUri = Uri.withAppendedPath(rawContactUri, RawContacts.Entity.CONTENT_DIRECTORY);
//            Cursor c1 = getContentResolver().query(entityUri,
//                    new String[]{
//                            RawContacts.SOURCE_ID,
//                            RawContactsEntity.DATA_ID,
//                            RawContactsEntity.MIMETYPE,
//                            RawContactsEntity.DATA1,
//                            RawContactsEntity.DELETED
//                    }, null, null, null);
//
//
//            try {
//                while (c1.moveToNext()) {
//                    String sourceId = c1.getString(0);
//                    Log.d("RawContactsEntity", "Raw sourceId: " + sourceId);
//                    if (!c1.isNull(1)) {
//                        String dataid = c1.getString(1);
//                        String mimeType = c1.getString(2);
//                        String data = c1.getString(3);
//                        String deleted = c1.getString(4);
//                        Log.d("RawContactsEntity", "Raw mimeType: " + dataid);
//                        Log.d("RawContactsEntity", "Raw mimeType: " + mimeType);
//                        Log.d("RawContactsEntity", "Raw data: " + data);
//                        Log.d("RawContactsEntity", "Raw data: " + deleted);
////                        contactModel = new ContactModel(data,deleted,);
//                    }
//                }
//            } finally {
//                c1.close();
//            }
//        }

//        // Query for ContactsContract.Data table
//        Cursor c2 = getContentResolver().query(Data.CONTENT_URI,
//                new String[]{Data._ID, Phone.NUMBER,
//                        Phone.TYPE, Phone.LABEL, Data.RAW_CONTACT_ID},
//                null,
//                null, null);
//        if (c2.getCount() > 0) {
//            while (c2.moveToNext()) {
//                Log.d("MYNEWID", "ContactsContract_ID: " + c2.getString(0));
//                Log.d("MYNEWID", "ContactsContract_ID: " + c2.getColumnName(0));
//                Log.d("MYNEWID", "RAW_CONTACT_ID: " + c2.getString(4));
//                Log.d("MYNEWID", "RAW_CONTACT_ID: " + c2.getColumnName(4));
//                Log.d("MYNEWID", "NUMBER: " + c2.getString(1));
//                Log.d("MYNEWID", "--------------------------------");
//            }
//        }
//        Log.d("MYID", "mContactsId: " + mContactsId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
