package com.example.ticknote;

import android.app.SearchManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;


import com.example.ticknote.NotesContract.TickNote;

public class NotesList extends AppCompatActivity {

    // For logging and debugging
    private static final String TAG = "NotesList";

    /**
     * The columns needed by the cursor adapter
     */
    private static final String[] PROJECTION = new String[] {
            TickNote._ID, // 0
            TickNote.COLUMN_NAME_TITLE, // 1
            TickNote.COLUMN_NAME_DATE,//3
    };

    /** The index of the title column */
    private static final int COLUMN_INDEX_TITLE = 1;
    private static final int COLUMN_INDEX_DATE = 3;

    private ListView listview;
    private SimpleCursorAdapter mAdapter;

    /**
     * onCreate is called when Android starts this Activity from scratch.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);
        // The user does not need to hold down the key to use menu shortcuts.
        setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);

        /* If no data is given in the Intent that started this Activity, then this Activity
         * was started when the intent filter matched a MAIN action. We should use the default
         * provider URI.
         */
        // Gets the intent that started this Activity.
        Intent intent = getIntent();

        // If there is no data associated with the Intent, sets the data to the default URI, which
        // accesses a list of notes.
        if (intent.getData() == null) {
            intent.setData(TickNote.CONTENT_URI);
        }

        /*
         * Sets the callback for context menu activation for the ListView. The listener is set
         * to be this Activity. The effect is that context menus are enabled for items in the
         * ListView, and the context menu is handled by a method in NotesList.
         */
        listview = findViewById(R.id.notes_list);
        listview.setOnCreateContextMenuListener(this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Constructs a new URI from the incoming URI and the row ID
                Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);

                // Gets the action from the incoming Intent
                String action = getIntent().getAction();

                // Handles requests for note data
                if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {

                    // Sets the result to return to the component that called this Activity. The
                    // result contains the new URI
                    setResult(RESULT_OK, new Intent().setData(uri));
                } else {

                    // Sends out an Intent to start an Activity that can handle ACTION_EDIT. The
                    // Intent's data is the note ID URI. The effect is to call NoteEdit.
                    startActivity(new Intent(Intent.ACTION_EDIT, uri));
                }
            }
        });

        /* Performs a managed query. The Activity handles closing and requerying the cursor
         * when needed.
         *
         * Please see the introductory note about performing provider operations on the UI thread.
         */
        Cursor cursor = managedQuery(
                getIntent().getData(),            // Use the default content URI for the provider.
                PROJECTION,                       // Return the note ID and title for each note.
                null,                             // No where clause, return all records.
                null,                             // No where clause, therefore no where column values.
                TickNote.DEFAULT_SORT_ORDER  // Use the default sort order.
        );

        /*
         * The following two arrays create a "map" between columns in the cursor and view IDs
         * for items in the ListView. Each element in the dataColumns array represents
         * a column name; each element in the viewID array represents the ID of a View.
         * The SimpleCursorAdapter maps them in ascending order to determine where each column
         * value will appear in the ListView.
         */

        // The names of the cursor columns to display in the view, initialized to the title column
        String[] dataColumns = { TickNote.COLUMN_NAME_TITLE ,TickNote.COLUMN_NAME_DATE} ;

        // The view IDs that will display the cursor columns, initialized to the TextView in
        // noteslist_item.xml
        int[] viewIDs = { R.id.note_title,R.id.note_date };

        // Creates the backing adapter for the ListView.
         mAdapter
                = new SimpleCursorAdapter(
                this,                             // The Context for the ListView
                R.layout.notes_list_item,          // Points to the XML for a list item
                cursor,                           // The cursor to get items from
                dataColumns,
                viewIDs
        );


        // Sets the ListView's adapter to be the cursor adapter that was just created.
        listview.setAdapter(mAdapter);

        // set filter for search view
        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return getCursor(constraint.toString());
            }
        });
    }



    private Cursor getCursor(String str) {
        Cursor mCursor = null;
        if (str == null  ||  str.length () == 0)  {
            Uri uri = TickNote.CONTENT_URI;
            String[] projection = new String[]{TickNote._ID,TickNote.COLUMN_NAME_TITLE,TickNote.COLUMN_NAME_DATE};
            String selection = null;
            String[] selectionArgs = null;
            String sortOrder = TickNote.DEFAULT_SORT_ORDER;
            mCursor = getContentResolver().query(uri, projection, selection, selectionArgs,
                    sortOrder);
        }
        else {
            Uri uri = TickNote.CONTENT_URI;
            String[] projection = new String[]{TickNote._ID,TickNote.COLUMN_NAME_TITLE,TickNote.COLUMN_NAME_DATE};
            String selection = "INSTR("+TickNote.COLUMN_NAME_TITLE+",?)>0";
            String[] selectionArgs = {str};
            String sortOrder = TickNote.DEFAULT_SORT_ORDER;
            mCursor = getContentResolver().query(uri, projection, selection, selectionArgs,
                    sortOrder);
        }

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
    /*
    @Override
    protected void onResume(){
        super.onResume();
        RefreshNotesList();
    }*/

    /**
     *  构造 listview
     */
    /*
    public void RefreshNotesList(){
        String [] projection = {
                BaseColumns._ID,
                TickNote.COLUMN_NAME_TITLE,
                TickNote.COLUMN_NAME_DATE
        };

        // Filter results WHERE "title" = 'My Title'
        //String selection = TickNote.COLUMN_NAME_TITLE+" =  ?";
        //String selectionArgs = {};

        Cursor cursor = notesDb.query(
                TickNote.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        // The names of the cursor columns to display in the view, initialized to the title column
        String[] dataColumns = { TickNote.COLUMN_NAME_TITLE,TickNote.COLUMN_NAME_DATE } ;
        // The view IDs that will display the cursor columns, initialized to the TextView in
        // noteslist_item.xml
        int[] viewIDs = {R.id.note_title,R.id.note_date };

        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(
                this,                             // The Context for the ListView
                R.layout.notes_list_item,          // Points to the XML for a list item
                cursor,                           // The cursor to get items from
                dataColumns,
                viewIDs
        );
        // Sets the ListView's adapter to be the cursor adapter that was just created.
        listview.setAdapter(adapter);
    }*/


    /**
     *  OptionsMenu 操作
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_list_options_menu, menu);

        /**
         *Associate searchable configuration with the SearchView
         */
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_note:
                startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
                return true;
            case R.id.menu_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *  ContextMenu 操作
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_list_context_menu,menu);
    }

}
