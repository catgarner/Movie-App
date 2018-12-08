package com.example.c1637.movie_assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
    }

    public void onClick(View view)
    {
        Intent intent = new Intent(this, MovieInfo.class);
        startActivity(intent);

        Context context = getApplicationContext();
        String text = "Fuck";
        int duration = Toast.LENGTH_SHORT;

        switch (view.getId())
        {
            case R.id.image_grinch:{
                text = "The Grinch";
                break;
            }
        }

        Toast.makeText(context, text, duration).show();

    }
}
