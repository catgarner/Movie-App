package com.example.c1637.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

    }

    public void onClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Context context = getApplicationContext();
        String text = "";
        int duration = Toast.LENGTH_SHORT;

        switch (view.getId())
        {
            case R.id.Grinch:{
                text = "";
                break;
            }
        }


        Toast toast = Toast.makeText(context, text, duration);//Make it
        toast.show();//Show it
    }
}
