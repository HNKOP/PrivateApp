package com.privateapp.privateapp.DATABASE;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.privateapp.privateapp.OBJECTS.Hero;

import java.util.ArrayList;
import java.util.List;

public class SQLiteClass {
    static SQLiteDatabase database;
    Context context;

    public SQLiteClass(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase("privateapp.db",context.MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS heroes (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LEVEL INTEGER, STR INTEGER, AGL INTEGER, INT INTEGER)");
    }

    public static void InsertHero(Hero hero)
    {
        database.execSQL("INSERT INTO heroes VALUES ('" + hero.getName() + "'," + String.valueOf(hero.getLevel()) + "," + String.valueOf(hero.getStrength()) + "," + String.valueOf(hero.getAgility()) + "," + String.valueOf(hero.getIntelligence())+ ");");
    }
    public static void CreateTestheroes()
    {
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('Lancelot', 10,5,5,5);");
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('Rafael', 5,15,2,4);");
        database.execSQL("INSERT INTO heroes (NAME,LEVEL,STR,AGL,INT) VALUES ('Goku', 9000,150,100,20);");
    }
    public static List<Hero> GetAllHeroes()
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
    public static void Close()
    {
        database.close();
    }
}
