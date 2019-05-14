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
