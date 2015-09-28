package com.gooner10.contactsprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

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

        String phoneNumber = null;
        String email = null;

        // Contacts Content URI
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        Uri RAW_CONTENT_URI = RawContacts.CONTENT_URI;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;


        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        String LAST_TIME_CONTACTED = ContactsContract.Contacts.LAST_TIME_CONTACTED;


        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

//        StringBuffer output = new StringBuffer();

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        ArrayList<String> mContactsId = new ArrayList<>();

//        long rawContactId = 0;
//        Cursor c = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
//                new String[]{ContactsContract.RawContacts._ID},
//                ContactsContract.RawContacts.CONTACT_ID + "=?",
//                new String[]{String.valueOf(contactId)}, null);

//        Cursor cursorq = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
//                query.PROJECTION, UserIdQuery.SELECTION, new String[]{username}, null);
//
//        try {
//            if (cursor != null && cursor.moveToFirst()) {
//                contactId = cursor.getLong(UserIdQuery.COLUMN_ID);
//            }
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        return authorId;

        // Loop for every contact in the phone
//        Uri rawContactUri = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, rawContactId);
//        Uri entityUri = Uri.withAppendedPath(rawContactUri, ContactsContract.RawContacts.Entity.CONTENT_DIRECTORY);
//        Cursor c = getContentResolver().query(entityUri,
//                new String[]{ContactsContract.RawContacts.SOURCE_ID, ContactsContract.RawContacts.Entity.DATA_ID,
//                        ContactsContract.RawContacts.Entity.MIMETYPE, ContactsContract.RawContacts.Entity.DATA1,
//                        ContactsContract.Contacts.Entity.DELETED},
//                null, null, null);
//
//        try {
//            while (c.moveToNext()) {
//                String sourceId = c.getString(0);
//                Log.d("TAG","sourceId: "+sourceId);
//                if (!c.isNull(1)) {
//                    String mimeType = c.getString(2);
//                    String data = c.getString(3);
//                    Log.d("TAG","data: "+data);
//                }
//            }
//        } finally {
//            c.close();
//        }




        // Contacts Query
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                double last_contacted = Double.parseDouble(cursor.getString(cursor.getColumnIndex(LAST_TIME_CONTACTED)));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    outputText.setText(name);
                    Log.d("TAG", "Name: " + name);
                    Log.d("TAG", "Last Contacted: " + last_contacted);
//                    String rawContactId = cursor.getString(cursor.getColumnIndex(_ID));
                    Log.d("TAG", "Last Contacted ID: " + contact_id);

//                    Cursor c = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
//                            new String[]{ContactsContract.RawContacts._ID},
//                            ContactsContract.RawContacts.CONTACT_ID + "=?",
//                            new String[]{String.valueOf(rawContactId)}, null);

//                    Uri rawContactUri = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, Long.parseLong(rawContactId));
//                    Uri rawContactUri = RawContacts.CONTENT_URI.buildUpon()
//                            .appendQueryParameter(RawContacts.CONTACT_ID, rawContactId)
//                            .build();

//                    Log.d("RAW", "Raw rawContactUri: " + rawContactUri);
//                    Cursor rawcursor = getContentResolver().query(rawContactUri, UserIdQuery.PROJECTION, UserIdQuery.SELECTION, new String[] {username}, null);
                }
//                if (hasPhoneNumber > 0) {
//
//                    output.append("\n First Name:" + name);
//
//                    // Query and loop for every phone number of the contact
//                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
//
//                    while (phoneCursor.moveToNext()) {
//                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
//                        output.append("\n Phone number:" + phoneNumber);
//                    }
//
//                    phoneCursor.close();
//
//                    // Query and loop for every email of the contact
//                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
//
//                    while (emailCursor.moveToNext()) {
//
//                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
//
//                        output.append("\nEmail:" + email);
//
//                    }
//
//                    emailCursor.close();
//                }

//                output.append("\n");
            }
//            Log.d("TAG", ""+output);
//            outputText.setText(output);
        }


        // RawContacts Query
//        Uri entityUri = Uri.withAppendedPath(RAW_CONTENT_URI, RawContacts.Entity.CONTENT_DIRECTORY);
        Cursor c = getContentResolver().query(RAW_CONTENT_URI,
                new String[]{RawContacts.CONTACT_ID, RawContacts.DELETED, RawContacts._ID}, null, null, null);
        if (c.getCount() > 0) {
            try {
                while (c.moveToNext()) {
                    String sourceId = c.getString(2);
//                            if (Integer.parseInt(sourceId) < 1000) {
                    Log.d("MYID", "Contact_ID: " + c.getColumnName(2));
//                    Log.d("MYID", "Raw Contact_ID: " + c.getColumnName(3));
                    Log.d("RAW", "Raw sourceId: " + sourceId.getClass());
                    Log.d("RAW", "Raw Deleted: " + c.getString(1));
                    outputText.setText(c.getString(0));
                    mContactsId.add(sourceId);
//                            }
//                            if (!c.isNull(1)) {
//                                String mimeType = c.getString(2);
//                                String data = c.getString(3);
//                            }
                }
            } finally {
                c.close();
            }
        }

//        for (String rawContactId : mContactsId) {
////            long rawContactIdd = Long.parseLong(Cursor.getString(anyCursor.getColumnIndex(RawContacts.CONTACT_ID)));
//
////            Uri entityUri = Uri.withAppendedPath(RAW_CONTENT_URI, rawContactId);
//            Uri rawContactUri = ContentUris.withAppendedId(RawContacts.CONTENT_URI, Long.parseLong(rawContactId));
//            Uri entityUrii = Uri.withAppendedPath(rawContactUri, RawContacts.Entity.CONTENT_DIRECTORY);
//
//            Log.d("TERO", "rawContactUri: " + rawContactUri.toString());
//            Log.d("TERO", "entityUri: " + entityUrii.toString());
//        }
//
//        for (String rawContactId : mContactsId) {
//            Log.d("RAW", "rawContactId: " + rawContactId);
//            Cursor c1 = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
//                    new String[]{ContactsContract.Data._ID, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE,
//                            ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED},
//                    ContactsContract.Data.RAW_CONTACT_ID + "=?" + " AND "
//                            + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
//                    new String[]{String.valueOf(rawContactId)}, null);
//            if (c1.getCount() > 0) {
//                c1.moveToNext();
//                Log.d("MYID", "ContactsContract_ID: " + c1.getString(0));
//                Log.d("MYID", "ContactsContract_ID: " + c1.getColumnName(0));
//                Log.d("MYID","NUMBER: "+c1.getString(1));
//            }
////            if(c1.getCount() >0){
////                while (c1.moveToNext()){
////                    Log.d("MYID","ContactsContract_ID: "+c1.getString(0));
////                    Log.d("MYID","ContactsContract_ID: "+c1.getColumnName(0));
////                    Log.d("RAW","NUMBER: "+c1.getString(1));
////                }
////            }
//        }


        Log.d("MYID", "mContactsId: " + mContactsId);
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
