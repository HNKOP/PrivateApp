package com.privateapp.privateapp;

        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Toast;

        import com.privateapp.privateapp.ADAPTERS.HeroAdapter;
        import com.privateapp.privateapp.OBJECTS.Hero;

        import java.util.ArrayList;
        import java.util.List;


public class ChooseProfileActivity extends AppCompatActivity {
    SQLiteDatabase database;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Hero> heroeslist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseprofile_layout);
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
        try
        {


        database = getBaseContext().openOrCreateDatabase("privateapp.db",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS heroes (name TEXT, level INTEGER, strength REAL, agility REAL, intelligence REAL)");

//        database.execSQL("INSERT INTO heroes VALUES ('Lancelot', 10,5,5,5);");
//        database.execSQL("INSERT INTO heroes VALUES ('Rafael', 5,15,2,4);");
//        database.execSQL("INSERT INTO heroes VALUES ('Goku', 9000,150,100,20);");



        heroeslist = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM heroes;", null);

//            if(cursor.moveToFirst()){
//                Toast.makeText(this, cursor.getString(0), Toast.LENGTH_SHORT).show();
//            }

        if(cursor.moveToFirst()){
            do{
                heroeslist.add(new Hero(cursor.getString(0),cursor.getInt(1),100,cursor.getFloat(2),cursor.getFloat(3),cursor.getFloat(4)));
            }
            while(cursor.moveToNext());
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_heroeslist);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HeroAdapter(heroeslist);
        mRecyclerView.setAdapter(mAdapter);


            cursor.close();
        database.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void onChooseClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent);
    }
}