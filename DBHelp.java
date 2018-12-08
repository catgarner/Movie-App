package com.example.c1637.movie_assignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class DBHelp extends SQLiteOpenHelper
{
    public static final String ROWID = "ID";
    public static final int COL_ROWID = 0;

    public static final String TITLE = "TITLE";
    public static final int COL_TITLE = 1;

    public static final String DESCRIPTION = "DESCRIPTION";
    public static final int COL_DESCRIPTION = 2;

    public static final String[] ALL_ROWS = new String[] {ROWID, TITLE, DESCRIPTION};

    public static final String DATABASE_NAME = "MOVIES";
    public static final String TABLE_NAME = "MOVIE_INFO";

    public DBHelp(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDB(String title, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor DisplayAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = null;
        Cursor c = 	db.query(true, TABLE_NAME, ALL_ROWS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void DeleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = DisplayAll();
        
    }


}
