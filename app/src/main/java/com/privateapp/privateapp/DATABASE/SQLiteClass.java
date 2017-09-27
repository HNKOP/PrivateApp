package com.privateapp.privateapp.DATABASE;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.privateapp.privateapp.OBJECTS.Hero;

import java.util.ArrayList;
import java.util.List;

public class SQLiteClass {
    SQLiteDatabase database;
    Context context;

    public SQLiteClass(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase("privateapp.db",context.MODE_PRIVATE,null);
        InitTables();
    }
    public void InitTables()
    {
        //DB Heroes
        database.execSQL("CREATE TABLE IF NOT EXISTS heroes " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "level INTEGER, " +
                "str INTEGER, " +
                "agl INTEGER, " +
                "int INTEGER)");
        //DB Locations
        database.execSQL("CREATE TABLE IF NOT EXISTS locations " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT)");
    }
    public void InsertHero(Hero hero)
    {
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('" + hero.getName() + "'," + String.valueOf(hero.getLevel()) + "," + String.valueOf(hero.getStrength()) + "," + String.valueOf(hero.getAgility()) + "," + String.valueOf(hero.getIntelligence())+ ");");
    }
    public void DeleteHero(int id)
    {
        database.execSQL("DELETE FROM heroes WHERE ID = " + String.valueOf(id)+";");
    }
    public String GetNameById(int id)
    {
        try
        {
            Cursor cursor = database.rawQuery("SELECT * FROM heroes WHERE ID = "+ String.valueOf(id) + ";", null);
            if(cursor.moveToFirst()){
                return cursor.getString(1);
            }
        }
        catch (Exception e)
        {
            return e.toString();
        }

        return "Not found";
    }
    public void CreateTestheroes()
    {
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('Lancelot', 10,5,5,5);");
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('Rafael', 5,15,2,4);");
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('Goku', 9000,150,100,20);");
    }
    public List<Hero> GetAllHeroes()
    {
        Cursor cursor = database.rawQuery("SELECT * FROM heroes;", null);
        List<Hero> heroeslist = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                heroeslist.add(new Hero(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),100,cursor.getInt(3),cursor.getInt(4),cursor.getInt(5)));
        }
            while(cursor.moveToNext());
        }
        cursor.close();
        return heroeslist;
    }
    public void Close()
    {
        database.close();
    }
}
