package com.example.c1637.movie_assignment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Display_Movie extends AppCompatActivity
{
    DBHelp myDB;
    TextView textdisplay;
    Button add_list, go_list, delete_list, rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display__movie);

        Intent intent = getIntent();
        int intValue = intent.getIntExtra("movie_id", 0);

        myDB = new DBHelp(this);
        textdisplay = findViewById(R.id.Text_Display);
        add_list = findViewById(R.id.add_list);
        delete_list = findViewById(R.id.delete_list);
        go_list = findViewById(R.id.go_list);
        rating = findViewById(R.id.rating);

        Cursor cursor = myDB.DisplayOne(intValue);
        displayRecordSet(cursor);

        int check = myDB.CheckForList(intValue);

        if(check == 0) {
            add_list.setVisibility(add_list.VISIBLE);
            delete_list.setVisibility(delete_list.INVISIBLE);
        }
        else
        {
            delete_list.setVisibility(delete_list.VISIBLE);
            add_list.setVisibility(add_list.INVISIBLE);
        }


        AddList();
        DeleteList();

    }


    private void displayText(String message) {
        textdisplay.setText(message);
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

   public void AddList()
    {
        add_list.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = getIntent();
                        Integer intValue = intent.getIntExtra("movie_id", 0);
                        boolean isInserted = myDB.updateList(intValue);

                        if(isInserted == true)
                            Toast.makeText(Display_Movie.this,"Added to My Favourites",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Display_Movie.this,"Not Added to My Favourites",Toast.LENGTH_LONG).show();

                        int check = myDB.CheckForList(intValue);
                        if(check == 0) {
                            add_list.setVisibility(add_list.VISIBLE);
                            delete_list.setVisibility(delete_list.INVISIBLE);
                        }
                        else
                        {
                            delete_list.setVisibility(delete_list.VISIBLE);
                            add_list.setVisibility(add_list.INVISIBLE);
                        }
                    }
                }
        );
    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.go_list: {
                Intent intent = new Intent(this, MyList.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void DeleteList()
    {
        delete_list.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = getIntent();
                        Integer intValue = intent.getIntExtra("movie_id", 0);
                        boolean Deleted = myDB.DeleteList(intValue);
                        if(Deleted)
                            Toast.makeText(Display_Movie.this,"Deleted from My Favourites",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Display_Movie.this,"Not deleted from My Favourites",Toast.LENGTH_LONG).show();

                        int check = myDB.CheckForList(intValue);

                        if(check == 0) {
                            add_list.setVisibility(add_list.VISIBLE);
                            delete_list.setVisibility(delete_list.INVISIBLE);
                        }
                        else
                        {
                            delete_list.setVisibility(delete_list.VISIBLE);
                            add_list.setVisibility(add_list.INVISIBLE);
                        }
                    }
                }
        );

    }
}
