package com.privateapp.privateapp;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView imageview;
    boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);


        imageview = (ImageView) findViewById(R.id.second_image);


    }
    public void initSong(View view)
    {
        try
        {
            if(isStart)
            {

                mediaPlayer.start();
                isStart = false;

            }
            else
            {
                isStart = true;
                mediaPlayer.pause();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }

}
