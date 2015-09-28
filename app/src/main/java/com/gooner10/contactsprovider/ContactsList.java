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

                }
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
                    }
                } finally {
                    c.close();
                }
            }
        }


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
