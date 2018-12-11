package com.example.c1637.movie_assignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyList extends AppCompatActivity
{
    DBHelp myDB;
    TextView listdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list);

        myDB = new DBHelp(this);
        listdisplay = (TextView)findViewById(R.id.List_Display);

        Cursor cursor = myDB.DisplayList(1);
        displayRecordSet(cursor);
    }

    private void displayText(String message) {
        listdisplay.setText(message);
    }

    private void displayRecordSet(Cursor cursor) {
        String message = "";

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(DBHelp.COL_ROWID);
                String title = cursor.getString(DBHelp.COL_TITLE);
                String description = cursor.getString(DBHelp.COL_DESCRIPTION);
                String genre = cursor.getString(DBHelp.COL_GENRE);
                String cast = cursor.getString(DBHelp.COL_PEOPLE);
                String length = cursor.getString(DBHelp.COL_LENGTH);
                String age = cursor.getString(DBHelp.COL_AGE);
                String list = cursor.getString(DBHelp.COL_LIST);

                // Append data to the message:
                message += "Id: " + id
                        +"\nTitle: " + title
                        +"\nDescription: " + description
                        +"\nGenre: " + genre
                        +"\nCast: " + cast
                        +"\nLength: " + length
                        +"\nAge: " + age
                        +"\n\n";
            } while(cursor.moveToNext());
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();

        displayText(message);
    }
}
