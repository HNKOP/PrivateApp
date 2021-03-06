package com.privateapp.privateapp;

import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {


    TextView data;
    ImageView imgdata;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textview = (TextView) findViewById(R.id.nametextview);


        initBackgroundAnimation();

    }

    private void initBackgroundAnimation()
    {
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
      //  final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();

                textview.setText(String.valueOf(progress));

                float translationX;
                if(progress > 0.5)
                {
                    translationX = width * (-progress + 1f) * 0.1f;


                }
                else
                {
                    translationX = width * progress * 0.1f;

                }
                backgroundOne.setTranslationX(translationX);
               // backgroundTwo.setTranslationX(translationX - width);


            }
        });
        animator.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        try
        {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }



    public void onClick(View view)
    {


//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);


    }



    class AsyncImage extends AsyncTask<String,Void,Drawable>
    {
        @Override
        protected Drawable doInBackground(String... params) {

            try
            {
                Bitmap x;

                HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();

                x = BitmapFactory.decodeStream(input);
                return new BitmapDrawable(x);
            }
            catch (Exception e)
            {
                return  null;
            }

        }
    }


}
