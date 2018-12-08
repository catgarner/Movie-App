package com.example.c1637.movie_assignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieInfo extends AppCompatActivity
{
    DBHelp myDB;
    EditText texttitle, textdescription;
    TextView textdisplay;
    Button add, display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        myDB = new DBHelp(this);
        texttitle = (EditText)findViewById(R.id.Title_Text);
        textdescription = (EditText)findViewById(R.id.Desc_Text);
        add = (Button)findViewById(R.id.Add);
        display = (Button)findViewById(R.id.Display);
        textdisplay = (TextView)findViewById(R.id.Text_Display);

        AddData();
        DisplayAll();
    }

    public void AddData()
    {
        add.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        boolean isInserted = myDB.insertDB(texttitle.getText().toString(), textdescription.getText().toString());
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

                // Append data to the message:
                message += "Id: " + id
                        +"\nTitle: " + title
                        +"\nDescription: " + description
                        +"\n\n";
            } while(cursor.moveToNext());
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();

        displayText(message);
    }
}
