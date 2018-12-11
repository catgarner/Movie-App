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
        Context context = getApplicationContext();
        String text = "Didnt Work";
        int duration = Toast.LENGTH_SHORT;

        switch (view.getId())
        {
            case R.id.image_goodfellas:{
                text = "Goodfellas";
                Intent intent = new Intent(this, MovieInfo.class);
                //intent.putExtra("movie_id", 1);

                startActivity(intent);
                break;
            }

            case R.id.image_halloween:{
                text = "Halloween";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 2);

                startActivity(intent);
                break;
            }

            case R.id.image_interstella:{
                text = "Interstella";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 3);

                startActivity(intent);
                break;
            }

            case R.id.image_fockers:{
                text = "Meet the Fockers";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 4);

                startActivity(intent);
                break;
            }

            case R.id.image_shutter:{
                text = "Shutter Island";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 5);

                startActivity(intent);
                break;
            }

            case R.id.image_green:{
                text = "The Green Mile";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 6);

                startActivity(intent);
                break;
            }

            case R.id.image_hangover:{
                text = "The Hangover";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 7);

                startActivity(intent);
                break;
            }

            case R.id.image_incredibles:{
                text = "The Incredibles 2";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 8);

                startActivity(intent);
                break;
            }

            case R.id.image_prestige:{
                text = "The Prestige";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 9);

                startActivity(intent);
                break;
            }

            case R.id.image_grinch:{
                text = "The Grinch";
                Intent intent = new Intent(this, Display_Movie.class);
                intent.putExtra("movie_id", 10);

                startActivity(intent);
                break;
            }
        }

        Toast.makeText(context, text, duration).show();

    }
}
