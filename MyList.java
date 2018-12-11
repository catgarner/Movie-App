package com.example.c1637.movie_assignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyList extends AppCompatActivity
{
    DBHelp myDB;
    TextView listdisplay;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list);

        myDB = new DBHelp(this);
        listdisplay = findViewById(R.id.List_Display);
        delete = findViewById(R.id.Delete);

        Cursor cursor = myDB.DisplayList(1);
        displayRecordSet(cursor);

        DeleteAll();
    }

    private void displayText(String message) {
        listdisplay.setText(message);
    }

    private void displayRecordSet(Cursor cursor) {
        String message = "";

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(DBHelp.COL_TITLE);
                String description = cursor.getString(DBHelp.COL_DESCRIPTION);
                String genre = cursor.getString(DBHelp.COL_GENRE);
                String cast = cursor.getString(DBHelp.COL_PEOPLE);
                String length = cursor.getString(DBHelp.COL_LENGTH);
                String age = cursor.getString(DBHelp.COL_AGE);
                int rate = cursor.getInt(DBHelp.COL_RATING);

                // Append data to the message:
                message += "Title: " + title
                        +"\nDescription: " + description
                        +"\nGenre: " + genre
                        +"\nCast: " + cast
                        +"\nLength: " + length
                        +"\nAge Rating: " + age
                        +"\nMy Rating: " + rate
                        +"\n\n";
            } while(cursor.moveToNext());
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();

        displayText(message);
    }

    public void DeleteAll()
    {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        displayText("Deleted all from My Favs");
                        myDB.DeleteAll();

                    }
                }
        );

    }
}
