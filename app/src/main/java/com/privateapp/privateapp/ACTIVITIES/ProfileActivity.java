package com.privateapp.privateapp.ACTIVITIES;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.privateapp.privateapp.ACTIVITIES.BattleActivity;
import com.privateapp.privateapp.ACTIVITIES.MapActivity;
import com.privateapp.privateapp.ADAPTERS.HeroAdapter;
import com.privateapp.privateapp.DATABASE.SQLiteClass;
import com.privateapp.privateapp.FRAGMENTS.DescriptionFragment;
import com.privateapp.privateapp.FRAGMENTS.LocationFragment;
import com.privateapp.privateapp.FRAGMENTS.StatusFragment;
import com.privateapp.privateapp.R;


public class ProfileActivity extends AppCompatActivity {
    TextView descriptionview, nameview;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor;
    FragmentManager manager;
    StatusFragment statusfragment;
    DescriptionFragment descriptionFragment;
    LocationFragment locationFragment;
    FragmentTransaction fragmentTransaction;
    Boolean exit = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        sharedPreferences = getSharedPreferences("PrivatePref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        manager = getSupportFragmentManager();
        statusfragment =  new StatusFragment();
        descriptionFragment = new DescriptionFragment();
        locationFragment = new LocationFragment();

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


        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed()
    {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Нажмите еще раз для Выхода.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

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

            nameview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    descriptionFragment.SetText("d");
                }
            });


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
                try
                {
                    if(!descriptionFragment.isAdded())
                    {
                       FillDescription filldesc = new FillDescription(); //Дополнительный поток добавляет фрагмент, после добавления правит текст.
                        filldesc.execute("Туловище");

                    }
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    try
                                    {

                                    }
                                    catch (Exception e)
                                    {

                                    }


                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                descriptionview.setText("Туловищеname \nСтата \t1: 0\nСтата \t2: \nСтата \t\t3:" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n a" +
                        "\n b");
                break;
            case R.id.imageview_head:
                try
                {
                    if(!descriptionFragment.isAdded())
                    {
                        FillDescription filldesc = new FillDescription(); //Дополнительный поток добавляет фрагмент, после добавления правит текст.
                        filldesc.execute("Голова");

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                descriptionview.setText("Головаname \nСтата 1: 0\nСтата 2: \nСтата 3:");
                break;
            case R.id.imageview_righthand:
                try
                {
                    if(!descriptionFragment.isAdded())
                    {
                        FillDescription filldesc = new FillDescription(); //Дополнительный поток добавляет фрагмент, после добавления правит текст.
                        filldesc.execute("Пр. рука");
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                descriptionview.setText("Праваярукаname \nСтата 1: 0\nСтата 2: \nСтата 3:");
                break;
            case R.id.imageview_lefthand:
                try
                {
                    if(!descriptionFragment.isAdded())
                    {
                        FillDescription filldesc = new FillDescription(); //Дополнительный поток добавляет фрагмент, после добавления правит текст.
                        filldesc.execute("Л. рука");
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
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

    public class FillDescription extends AsyncTask<String,Void,Void>
    {
        String text;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... voids) {
            text = voids[0];
            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.description_cont,descriptionFragment);
            fragmentTransaction.commit();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            descriptionFragment.SetText(text); //Здесь передается инфа из бд??
            super.onPostExecute(aVoid);
        }
    }
}
