package com.privateapp.privateapp.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.privateapp.privateapp.ACTIVITIES.BattleActivity;
import com.privateapp.privateapp.ACTIVITIES.MapActivity;
import com.privateapp.privateapp.R;


public class ProfileActivity extends AppCompatActivity {
    TextView descriptionview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        InitObjects();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void InitObjects() {
        descriptionview = (TextView) findViewById(R.id.description_textview);
    }

    public void onItemInvClick(View view) {
        //Toast.makeText(this, "View id: " + String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();
        int id = view.getId();
        switch (id) {
            case R.id.imageview_body:

                descriptionview.setText("Туловищеname \nСтата 1: 0\nСтата 2: \nСтата 3:");
                break;
            case R.id.imageview_head:

                descriptionview.setText("Головаname \nСтата 1: 0\nСтата 2: \nСтата 3:");
                break;
            case R.id.imageview_righthand:

                descriptionview.setText("Праваярукаname \nСтата 1: 0\nСтата 2: \nСтата 3:");
                break;
            case R.id.imageview_lefthand:

                descriptionview.setText("Леваярукаname \nСтата 1: 0\nСтата 2: \nСтата 3:");
                break;
        }
    }

    public void onBattleClick(View view) {
        Intent intent = new Intent(getApplicationContext(), BattleActivity.class);
        startActivity(intent);
    }

    public void onMapClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }
}
