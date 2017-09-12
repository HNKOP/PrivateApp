package com.privateapp.privateapp;

        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;




public class ChooseProfileActivity extends AppCompatActivity {
    SQLiteDatabase database;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseprofile_layout);
        initObjects();
    }

    public void initObjects() {
        database = getBaseContext().openOrCreateDatabase("privateapp.db",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS heroes (name TEXT, level INTEGER, strength REAL, agility REAL, intelligence REAL)");

        database.execSQL("INSERT INTO heroes VALUES ('Lancelot', 10,5,5,5);");
        database.execSQL("INSERT INTO heroes VALUES ('Rafael', 5,15,2,4);");
        database.execSQL("INSERT INTO heroes VALUES ('Goku', 9000,150,100,20);");

        database.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_heroeslist);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



    }
}