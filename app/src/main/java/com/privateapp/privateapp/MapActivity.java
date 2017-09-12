package com.privateapp.privateapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {
    WebView mapview;
    ImageView imageview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        initObjects();
    }

    public void initObjects() {
        mapview = (WebView) findViewById(R.id.webview_map);
        mapview.setBackgroundColor(Color.BLACK);
        mapview.getSettings().setSupportZoom(true);
        mapview.getSettings().setBuiltInZoomControls(true);
        mapview.loadUrl("file:///android_asset/russia.jpg");
        mapview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Toast.makeText(MapActivity.this, String.format(event.getX() + " : " + event.getY()), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        
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
}
