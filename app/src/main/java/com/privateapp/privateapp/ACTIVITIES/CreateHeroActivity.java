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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createhero_layout);
        initObjects();
    }

    public void initObjects() {
        editName = (EditText) findViewById(R.id.createhero_editname);
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
