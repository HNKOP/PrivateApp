package com.privateapp.privateapp.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.privateapp.privateapp.DATABASE.SQLiteClass;
import com.privateapp.privateapp.OBJECTS.Hero;
import com.privateapp.privateapp.R;

/**
 * Created by emilg on 16.09.2017.
 */

public class CreateHeroActivity extends AppCompatActivity {
    SQLiteClass sqLiteClass;
    EditText editName;
    View decorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createhero_layout);
        decorView = getWindow().getDecorView();
        initObjects();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void initObjects() {
        editName = (EditText) findViewById(R.id.createhero_editname);
    }

    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(getApplicationContext(),ChooseProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
    public void onCreateHeroClick(View view)
    {
        sqLiteClass = new SQLiteClass(getBaseContext());
        if(!editName.getText().toString().isEmpty())
        {
            Hero hero = new Hero(0,editName.getText().toString(),0,100,0,0,0);
            sqLiteClass.InsertHero(hero);
            Toast.makeText(this, "Персонаж создан", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ChooseProfileActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Введите имя персонажа", Toast.LENGTH_SHORT).show();
        }
    }
}
