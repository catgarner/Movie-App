package com.example.c1637.movie_assignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieInfo extends AppCompatActivity
{
    /*
    DBHelp myDB;
    EditText texttitle, textdescription, textpeople, textdirector;
    TextView textdisplay;
    Button add, display, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        myDB = new DBHelp(this);
        texttitle = (EditText)findViewById(R.id.Title_Text);
        textdescription = (EditText)findViewById(R.id.Desc_Text);
        textpeople = (EditText)findViewById(R.id.People_Text);
        textdirector = (EditText)findViewById(R.id.Text_Director);
        add = (Button)findViewById(R.id.Add);
        display = (Button)findViewById(R.id.Display);
        delete = (Button)findViewById(R.id.Delete);
        textdisplay = (TextView)findViewById(R.id.Text_Display);


        AddData();
        DisplayAll();
        DeleteAll();
    }

    public void AddData()
    {
        add.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        boolean isInserted = myDB.insertDB(texttitle.getText().toInt(), textdescription.getText().toString(), textpeople.getText().toString(), textdirector.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MovieInfo.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MovieInfo.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void DisplayAll()
    {
        display.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayText("Click Display");
                        Cursor cursor = myDB.DisplayAll();
                        displayRecordSet(cursor);

                        if(cursor != null)
                            Toast.makeText(MovieInfo.this,"Worked",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MovieInfo.this,"Didnt work ",Toast.LENGTH_LONG).show();


                    }
                }
        );

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
                int list = cursor.getInt(DBHelp.COL_LIST);
                int rating = cursor.getInt(DBHelp.COL_RATING);

                // Append data to the message:
                message += "Id: " + id
                        +"\nTitle: " + title
                        +"\nDescription: " + description
                        +"\nGenre: " + genre
                        +"\nCast: " + cast
                        +"\nLength: " + length
                        +"\nAge: " + age
                        +"\nList: " + list
                        +"\nRating: " + rating
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
                        displayText("Clicked clear all");
                        myDB.DeleteAll();

                    }
                }
        );

    }*/
}
