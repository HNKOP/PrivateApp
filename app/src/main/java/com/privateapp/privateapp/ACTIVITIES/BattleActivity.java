package com.privateapp.privateapp.ACTIVITIES;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.privateapp.privateapp.FRAGMENTS.LocationFragment;
import com.privateapp.privateapp.FRAGMENTS.StatusFragment;
import com.privateapp.privateapp.R;


public class BattleActivity extends AppCompatActivity {
    ProgressBar heroHp, enemyHp, progressTurn;
    float hp,totalherohp,totalenemyhp,damage,currentherohp,currentenemyhp;
    TextView textviewTurn;
    Button buttonTurn;
    FragmentManager manager;
    StatusFragment statusfragment;
    LocationFragment locationFragment;
    FragmentTransaction fragmentTransaction;
    ImageView arrowview;
    TranslateAnimation mAnimation;
    Integer[] location;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_layout);

        manager = getSupportFragmentManager();
        statusfragment =  new StatusFragment();
        locationFragment = new LocationFragment();

        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.status_cont,statusfragment);
        fragmentTransaction.commit();

        initObjects();
    }


    @Override
    protected void onResume() {
        super.onResume();
        try
        {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            TextView titleview = (TextView) findViewById(R.id.location_view);
            titleview.setText("Битва");

            final Button locationbutton = findViewById(R.id.openlocations_button);
            locationbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!locationFragment.isAdded())
                    {
                        fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.location_cont,locationFragment);
                        fragmentTransaction.commit();
                    }
                    else
                    {
                        fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.remove(locationFragment);
                        fragmentTransaction.commit();
                    }

                }
            });

            arrowview = (ImageView) findViewById(R.id.arrow_view);
            arrowview.setVisibility(View.GONE);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }



    public void initObjects() {
        heroHp = (ProgressBar) findViewById(R.id.progressBar_herohp);
        enemyHp = (ProgressBar) findViewById(R.id.progressBar_enemyhp);
        progressTurn = (ProgressBar) findViewById(R.id.progressBar_turn);
        progressTurn.setVisibility(View.GONE);
        textviewTurn = (TextView) findViewById(R.id.textview_turn);
        textviewTurn.setText("Твои ход");
        buttonTurn = (Button) findViewById(R.id.button_turn);
        totalherohp = 100f;
        totalenemyhp = 220f;
        heroHp.setProgress(100);
        enemyHp.setProgress(100);
        currentherohp = totalherohp;
        currentenemyhp = totalenemyhp;
        damage = 10;
        location = new Integer[4];
        location[0] = 0;
        location[1] = 800;
        location[2] = 300;
        location[3] = 300;
    }
    public void onDoDamageClick(View view)
    {
        EnemyTurn enemyTurn = new EnemyTurn();


        currentenemyhp -= damage;
        int currenthp = Math.round((currentenemyhp/totalenemyhp)*100);
        enemyHp.setProgress(currenthp);

        if(currentenemyhp <= 0)
        {
            Toast.makeText(this, "Вы победили", Toast.LENGTH_SHORT).show();
            currentenemyhp = totalenemyhp;
            enemyHp.setProgress(100);
        }


        //mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation = new TranslateAnimation(location[0],location[1],location[2],location[3]);
        mAnimation.setDuration(1000);
        mAnimation.setRepeatCount(0);
        arrowview.setAnimation(mAnimation);

        enemyTurn.execute();

    }

    public void onRadioButtonClick(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radiobutton_atkhead:
                if(checked)
                {
                    location[0] = 0;
                    location[1] = 800;
                    location[2] = 0;
                    location[3] = 0;
                }
             break;
            case R.id.radiobutton_atkbody:
                if(checked)
                {
                    location[0] = 0;
                    location[1] = 800;
                    location[2] = 300;
                    location[3] = 300;
                }
                break;
            case R.id.radiobutton_atklegs:
                if(checked)
                {
                    location[0] = 0;
                    location[1] = 800;
                    location[2] = 600;
                    location[3] = 600;
                }
                break;
            case R.id.radiobutton_defhead:
                if(checked)
                {

                }
                break;
            case R.id.radiobutton_defbody:
                if(checked)
                {

                }
                break;
            case R.id.radiobutton_deflegs:
                if(checked)
                {

                }
                break;
        }
    }
    class EnemyTurn extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressTurn.setVisibility(View.VISIBLE);
            textviewTurn.setText("Ход противника");
            buttonTurn.setEnabled(false);
            arrowview.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try
            {
               Thread.sleep(1000);
            }
            catch (Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            currentherohp -= damage;
            int currenthp = Math.round((currentherohp/totalherohp)*100);
            heroHp.setProgress(currenthp);
            buttonTurn.setEnabled(true);
            progressTurn.setVisibility(View.GONE);
            textviewTurn.setText("Твой ход");
            arrowview.setVisibility(View.GONE);

            if(currentherohp <= 0)
            {
                Toast.makeText(getApplicationContext(), "Вы проиграли", Toast.LENGTH_SHORT).show();
                currentherohp = totalherohp;
                heroHp.setProgress(100);
            }

        }
    }

}
