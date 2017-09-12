package com.privateapp.privateapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class BattleActivity extends AppCompatActivity {
    ProgressBar heroHp, enemyHp, progressTurn;
    float hp,totalherohp,totalenemyhp,damage,currentherohp,currentenemyhp;
    TextView textviewTurn;
    Button buttonTurn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_layout);
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


        enemyTurn.execute();

    }

    class EnemyTurn extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressTurn.setVisibility(View.VISIBLE);
            textviewTurn.setText("Ход противника");
            buttonTurn.setEnabled(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            try
            {
               Thread.sleep(2000);
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

            if(currentherohp <= 0)
            {
                Toast.makeText(getApplicationContext(), "Вы проиграли", Toast.LENGTH_SHORT).show();
                currentherohp = totalherohp;
                heroHp.setProgress(100);
            }

        }
    }

}
