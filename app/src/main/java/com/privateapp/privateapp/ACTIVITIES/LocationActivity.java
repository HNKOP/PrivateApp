package com.privateapp.privateapp.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.privateapp.privateapp.R;

/**
 * Created by emilg on 05.10.2017.
 */

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_location_layout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void onClickLocaton(View view)
    {
        Toast.makeText(this, "Вы выбрали 1 локацию", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), BattleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }
}
