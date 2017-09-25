package com.privateapp.privateapp.ACTIVITIES;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.privateapp.privateapp.ADAPTERS.HeroAdapter;
        import com.privateapp.privateapp.DATABASE.SQLiteClass;
        import com.privateapp.privateapp.OBJECTS.Hero;
        import com.privateapp.privateapp.R;
        import com.privateapp.privateapp.RecyclerTouchListener;

        import java.util.ArrayList;
        import java.util.List;


public class ChooseProfileActivity extends AppCompatActivity {
    //SQLiteDatabase database;

    View previousview;
    Drawable backgroundDrawable;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Hero> heroeslist;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseprofile_layout);
        sharedPreferences = getSharedPreferences("PrivatePref",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initObjects();
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

    public void initObjects() {
        final SQLiteClass sqLiteClass = new SQLiteClass(getBaseContext());
        try
        {


        //sqLiteClass.CreateTestheroes();

        heroeslist = sqLiteClass.GetAllHeroes();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_heroeslist);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if(heroeslist.size() > 0 )
        {
            mAdapter = new HeroAdapter(heroeslist);
            mRecyclerView.setAdapter(mAdapter);
        }

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final TextView txtName = view.findViewById(R.id.recycleitem_name);
                final TextView txtId = view.findViewById(R.id.recycleitem_id);


//                backgroundDrawable = view.getBackground();
//                if(previousview != null)
//                {
//                    previousview.setBackgroundDrawable(backgroundDrawable);
//                }
//
//
//                view.setBackgroundColor(Color.YELLOW);
//                previousview = view;

                Toast.makeText(ChooseProfileActivity.this,"Вы выбрали: " + txtName.getText().toString(), Toast.LENGTH_SHORT).show();
                try
                {
                    editor.putInt("PROFILE_ID",Integer.valueOf(txtId.getText().toString()));
                    editor.commit();
                }
                catch (Exception e)
                {
                    Toast.makeText(ChooseProfileActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(0,0);
                
            }

            @Override
            public void onLongClick(View view, int position) {
                final TextView txtId = view.findViewById(R.id.recycleitem_id);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                try
                                {
                                    int id = Integer.valueOf(txtId.getText().toString());
                                    SQLiteClass sqLiteClass1 = new SQLiteClass(getBaseContext());
                                    sqLiteClass1.DeleteHero(id);
                                    heroeslist = sqLiteClass1.GetAllHeroes();
                                    if(heroeslist.size() >= 0 )
                                    {
                                        mAdapter = new HeroAdapter(heroeslist);
                                        mRecyclerView.setAdapter(mAdapter);
                                    }
                                    sqLiteClass1.Close();
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(ChooseProfileActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                try
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Вы точно хотите удалить этот профиль?").setPositiveButton("Да", dialogClickListener)
                            .setNegativeButton("Нет", dialogClickListener).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(ChooseProfileActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }));
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        finally {
            sqLiteClass.Close();
        }
    }
    public void onChooseClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent);
    }
    public void onCreateHeroClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(),CreateHeroActivity.class);
        startActivity(intent);
    }
}