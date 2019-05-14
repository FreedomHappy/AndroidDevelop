# TickNote 期中实验
## Basic Features
### 1.Timestamp
* Step one : create date attribute

I use SQLite to save note modified time. 

That needs to create a date column in table(TickNote: I define table in [NotesContract.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/NotesContract.java))

```java
        public static final String TABLE_NAME = "ticknote";
        public static final String COLUMN_NAME_TITLE="title";
        public static final String COLUMN_NAME_CONTENT="content";
        public static final String COLUMN_NAME_DATE="date";
```

* Step two : save date ([NoteEdit.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/NoteEdit.java))

```java
        // Sets up a map to contain values to be updated in the provider.
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(System.currentTimeMillis());
        values.put(TickNote.COLUMN_NAME_DATE, date);
```

* Step three : read date from SQLite

We need define a PROJECTION contains date attribute

``` java
private static final String[] PROJECTION = new String[] {
            TickNote._ID, // 0
            TickNote.COLUMN_NAME_TITLE, // 1
            TickNote.COLUMN_NAME_DATE,//3
    };
```
Create a Cursor instance and use SimpleCursorAdapter for the listviwe([NotesList.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/NotesList.java)).

I apply ContentProvider([TickNoteProvider.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/TickNoteProvider.java))
to the application for data to read and write. 

``` java
        Cursor cursor = managedQuery(
                getIntent().getData(),            // Use the default content URI for the provider.
                PROJECTION,                       // Return the note ID and title for each note.
                null,                             // No where clause, return all records.
                null,    // No where clause, therefore no where column values.
                pref.getString(mListOrder,TickNote.DEFAULT_SORT_ORDER) // Use the default sort order.
        );
        
        ........
        // Creates the backing adapter for the ListView.
         mAdapter
                = new SimpleCursorAdapter(
                this,                             // The Context for the ListView
                R.layout.notes_list_item,          // Points to the XML for a list item
                cursor,                           // The cursor to get items from
                dataColumns,
                viewIDs
        );      
        
```
* Screenshot
### 2.Search note 
* Step one : add search view

Define a search item in [notes_list_options_menu.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/res/menu/notes_list_options_menu.xml)

```xml
        <item
        android:id="@+id/menu_search"
        android:icon="@drawable/ic_search_black_24dp"
        android:title="Search"
        app:actionViewClass="android.widget.SearchView"
        app:showAsAction="always" />
```

create [searchable.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/res/xml/searchable.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<searchable xmlns:android="http://schemas.android.com/apk/res/android"
    android:label="@string/app_name"
    android:hint="@string/search_hint" />
```
add meta-data in [AndroidManifest.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/AndroidManifest.xml)

```xml
          <meta-data
          android:name="android.app.searchable"
          android:resource="@xml/searchable" />
```

* Step two : set filter

implement in [NotesList.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/NotesList.java)

Associate  searchable configuration with the SearchView
```java
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
```

set filter for search view
```java
        // set filter for search view
        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return getCursor(constraint.toString());
            }
        });
```
create query way in getCursor(constrint.toString())
```java
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
```
* Screen shot

## Extended Features
### 1.switch editing's page background color
implement in [NoteEdit.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/NoteEdit.java)
* Step one : use AlertDialog interacting with users

[back_color_alert_dialog.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/res/layout/back_color_alert_dialog.xml)

BuildAlertDialog()
```java
public void BuildAlertDialog(){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.back_color_alert_dialog, null));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
```
* Step two : use SharePreference to save

get from SharePreference
```java
public void initScreenColor(){
        mEditScreen = findViewById(R.id.edit_screen);
        SharedPreferences pref = getSharedPreferences("data",
                MODE_PRIVATE);
        mEditScreen.setBackgroundColor(ContextCompat.getColor(this,
                pref.getInt(mScreenColor,R.color.color_white)));
    }
```

put into SharePreference
```java
public void onChangeBackgroundColor(View v){
        mEditScreen = findViewById(R.id.edit_screen);
        SharedPreferences.Editor editor = getSharedPreferences("data",
                MODE_PRIVATE).edit();
        switch (v.getId()){
            case R.id.blue_btn:
                mEditScreen.setBackgroundColor(ContextCompat.getColor(this, R.color.color_blue));
                editor.putInt(mScreenColor,R.color.color_blue);
                editor.commit();
                return;
            case R.id.white_btn:
                mEditScreen.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));
                editor.putInt(mScreenColor,R.color.color_white);
                editor.commit();
                return;
            case R.id.green_btn:
                mEditScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.color_green));
                editor.putInt(mScreenColor,R.color.color_green);
                editor.commit();
                return;
        }
    }
```
### 2. note list order
