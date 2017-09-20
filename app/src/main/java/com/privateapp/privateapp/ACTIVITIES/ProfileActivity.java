package com.privateapp.privateapp.ACTIVITIES;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.privateapp.privateapp.ACTIVITIES.BattleActivity;
import com.privateapp.privateapp.ACTIVITIES.MapActivity;
import com.privateapp.privateapp.DATABASE.SQLiteClass;
import com.privateapp.privateapp.FRAGMENTS.StatusFragment;
import com.privateapp.privateapp.R;


public class ProfileActivity extends AppCompatActivity {
    TextView descriptionview, nameview;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor;
    FragmentManager manager;
    StatusFragment statusfragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        sharedPreferences = getSharedPreferences("PrivatePref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        manager = getSupportFragmentManager();
        statusfragment =  new StatusFragment();
        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.status_cont,statusfragment);
        fragmentTransaction.commit();


        InitObjects();

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            TextView titleview = (TextView) findViewById(R.id.location_view);
            titleview.setText("Профиль");
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void InitObjects() {
        descriptionview = (TextView) findViewById(R.id.description_textview);
        nameview = (TextView) findViewById(R.id.title2_view);
        try
        {
            int id = sharedPreferences.getInt("PROFILE_ID",0);
            SQLiteClass sqLiteClass = new SQLiteClass(getBaseContext());
            nameview.setText(sqLiteClass.GetNameById(id));



        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

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
