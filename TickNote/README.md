# TickNote 期中实验
## Basic Features
### 1.Timestamp
* Step one: create date attribute
I use SQLite to save note modified time. That need to create a date column in table(TickNote: I define table in [NotesContract.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/TickNote/app/src/main/java/com/example/ticknote/NotesContract.java))
'''java
public static final String TABLE_NAME = "ticknote";
        public static final String COLUMN_NAME_TITLE="title";
        public static final String COLUMN_NAME_CONTENT="content";
        public static final String COLUMN_NAME_DATE="date";
'''
