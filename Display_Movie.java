package com.example.c1637.movie_assignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


public class Display_Movie extends AppCompatActivity
{
    //Setting variables for the items used on the xml and the database helper class
    DBHelp myDB;
    TextView textdisplay;
    Button add_list, go_list, delete_list, rating, setRating;
    NumberPicker np;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display__movie);

        //This gets the movie id that was sent from the home page in the intent and stores it in a variable
        Intent intent = getIntent();
        int intValue = intent.getIntExtra("movie_id", 0);

        myDB = new DBHelp(this);
        textdisplay = findViewById(R.id.Text_Display);
        add_list = findViewById(R.id.add_list);
        delete_list = findViewById(R.id.delete_list);
        go_list = findViewById(R.id.go_list);
        rating = findViewById(R.id.rating);
        setRating = findViewById(R.id.submit_rate);
        np = findViewById(R.id.numberpicker);

        //This sets the min and max values for the number picker for rating the movie
        np.setMinValue(1);
        np.setMaxValue(5);

        //This calls the function to display the information about the movie that was clicked
        //It sends the value that was taken in from the intent so that the function knows which movie information to display
        Cursor cursor = myDB.DisplayOne(intValue);
        //This then send the information that is returned to the variable.
        displayData(cursor);

        //This runs the function and sends the rowid variable taken in form the intent to the function.
        //It the stores what gets returned in a variable
        int check = myDB.CheckForList(intValue);

        //If no rows are returned then the add to favs button becomes visible and the delete button turn invisible
        //But if rows are returned the buttons are made the opposite visibility
        if(check == 0) {
            add_list.setVisibility(add_list.VISIBLE);
            delete_list.setVisibility(delete_list.INVISIBLE);
        }
        else
        {
            delete_list.setVisibility(delete_list.VISIBLE);
            add_list.setVisibility(add_list.INVISIBLE);
        }


        //These run the methods to add the on clicks to the buttons
        AddList();
        DeleteList();
        Rating();
        SetRating();

    }


    private void displayText(String message) {
        textdisplay.setText(message);
    }

    private void displayData(Cursor cursor) {
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
                message += "Title: " + title
                        +"\nDescription: " + description
                        +"\nGenre: " + genre
                        +"\nCast: " + cast
                        +"\nLength: " + length
                        +"\nAge Rating: " + age
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

    public void Rating()
    {
        rating.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        rating.setVisibility(rating.INVISIBLE);
                        setRating.setVisibility(setRating.VISIBLE);
                        np.setVisibility(np.VISIBLE);

                    }
                }

        );
    }

    public void SetRating()
    {
        setRating.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        np.setVisibility(np.INVISIBLE);
                        rating.setVisibility(rating.VISIBLE);
                        setRating.setVisibility(setRating.INVISIBLE);

                        Intent intent = getIntent();
                        Integer intValue = intent.getIntExtra("movie_id", 0);
                        boolean Rating = myDB.RateMovie(np.getValue(), intValue);
                        if(Rating)
                            Toast.makeText(Display_Movie.this,"Rated " + np.getValue() + " Star(s)",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Display_Movie.this,"Not Rated",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

}
