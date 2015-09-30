package com.gooner10.contactsprovider;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;

import static android.provider.ContactsContract.RawContactsEntity;

public class ContactsList extends AppCompatActivity {

    @Bind(R.id.recyclerViewMovie)
    RecyclerView mMovieRecyclerView;
    ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        // Initialize recycler view
        mMovieRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMovie);
        mMovieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Query to fetch Contacts
        fetchContacts();
    }

    public void fetchContacts() {

        Uri RAW_CONTENT_URI = RawContacts.CONTENT_URI;

        ArrayList<String> mContactsId = new ArrayList<>();
        ArrayList<ContactModel> mContactsList = new ArrayList<>();
        ContactModel contactModel = null;

        Cursor c = getContentResolver().query(RAW_CONTENT_URI,
                new String[]{RawContacts.DELETED, RawContacts.LAST_TIME_CONTACTED, RawContacts._ID, RawContacts.CONTACT_ID}, null, null, null);
        if (c.getCount() > 0) {
            try {
                while (c.moveToNext()) {
                    String sourceId = c.getString(2);
                    String lastContacted = (c.getString(1));
                    mContactsId.add(sourceId);

                    Uri rawContactUri = ContentUris.withAppendedId(RawContacts.CONTENT_URI, Long.parseLong(sourceId));
                    Uri entityUri = Uri.withAppendedPath(rawContactUri, RawContacts.Entity.CONTENT_DIRECTORY);

                    Cursor c1 = getContentResolver().query(entityUri,
                            new String[]{
                                    RawContacts.SOURCE_ID,
                                    RawContactsEntity.DATA_ID,
                                    RawContactsEntity._ID,
                                    RawContactsEntity.DATA1,
                                    RawContactsEntity.DELETED,
                                    RawContactsEntity.MIMETYPE,
                            }, null, null, null);
                    try {
                        while (c1.moveToNext()) {
                            if (!c1.isNull(1)) {
                                String dataid = c1.getString(1);
                                String id = c1.getString(2);
                                String data = c1.getString(3);
                                String deletedEntity = c1.getString(4);
                                String mimetype = c1.getString(5);
                                String mimetypecol = c1.getColumnName(5);

                                Log.d("RawContactsEntity", "RawContacts._ID: " + sourceId);
                                Log.d("RawContactsEntity", "Raw _ID: " + id);

                                Log.d("RawContactsEntity", "getColumnName._ID: " + mimetype);
                                Log.d("RawContactsEntity", "getColumnName._ID: " + mimetypecol);

                                Log.d("RawContactsEntity", "RawContacts._ID: " + sourceId);
                                Log.d("RawContactsEntity", "Raw DATA_ID: " + dataid);
                                Log.d("RawContactsEntity", "Raw _ID: " + id);
                                Log.d("RawContactsEntity", "Raw data: " + data);
//                                Log.d("RawContactsEntity", "Raw deleted: " + deleted);
                                Log.d("RawContactsEntity", "Raw deletedEntity: " + deletedEntity);
                                Log.d("RawContactsEntity", "Raw MIMETYPE: " + mimetype);
                                if (sourceId.equals(id)) {
                                    contactModel = new ContactModel(data, deletedEntity, lastContacted);
                                }
                            }
                        }
                    } finally {
                        c1.close();
                    }
                    if (contactModel != null) {
                        mContactsList.add(contactModel);
                    }
                }
            } finally {
                c.close();
            }
        }

        // Set data to Adapter
        mContactAdapter = new ContactAdapter(this, mContactsList);
        mMovieRecyclerView.setAdapter(mContactAdapter);

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
