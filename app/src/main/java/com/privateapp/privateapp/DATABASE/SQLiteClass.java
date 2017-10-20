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
        database.execSQL("CREATE TABLE IF NOT EXISTS enemies " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "level INTEGER, " +
                "str INTEGER, " +
                "agl INTEGER, " +
                "int INTEGER)");
        database.execSQL("CREATE TABLE IF NOT EXISTS items " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "description TEXT)");
        database.execSQL("CREATE TABLE IF NOT EXISTS enemieslocations " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_enemy INTEGER," +
                "id_location INTEGER)");
        database.execSQL("CREATE TABLE IF NOT EXISTS enemiesitems " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_enemy INTEGER," +
                "id_item INTEGER)");
        database.execSQL("CREATE TABLE IF NOT EXISTS heroesitems " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_hero INTEGER," +
                "id_item INTEGER)");
    }
    public void InsertHero(Hero hero)
    {
        database.execSQL("INSERT INTO heroes (name,level,str,agl,int) VALUES ('" + hero.getName() + "'," + String.valueOf(hero.getLevel()) + "," + String.valueOf(hero.getStrength()) + "," + String.valueOf(hero.getAgility()) + "," + String.valueOf(hero.getIntelligence())+ ");");
    }
    public void DeleteHero(int id)
    {
        database.execSQL("DELETE FROM heroes WHERE id = " + String.valueOf(id)+";");
    }
    public String GetNameById(int id)
    {
        try
        {
            Cursor cursor = database.rawQuery("SELECT * FROM heroes WHERE id = "+ String.valueOf(id) + ";", null);
            if(cursor.moveToFirst()){
                return cursor.getString(1);
            }
        }
        catch (Exception e)
        {
            return e.toString();
        }

        return "Error: not found";
    }
    public void CreateTestData()
    {
        database.execSQL("INSERT INTO heroes (name,level,str,agl,int) VALUES ('Lancelot', 10,5,5,5);");
        database.execSQL("INSERT INTO heroes (name,level,str,agl,int) VALUES ('Rafael', 5,15,2,4);");
        database.execSQL("INSERT INTO heroes (name,level,str,agl,int) VALUES ('Goku', 9000,150,100,20);");

        database.execSQL("INSERT INTO enemies (name,level,str,agl,int) VALUES ('Kusaka', 10,5,5,5);");
        database.execSQL("INSERT INTO enemies (name,level,str,agl,int) VALUES ('Grizyaka', 5,15,2,4);");
        database.execSQL("INSERT INTO enemies (name,level,str,agl,int) VALUES ('Cherepaha', 9000,150,100,20);");

        database.execSQL("INSERT INTO items (name,description) VALUES ('Меч', 'Чтобы рубить');");
        database.execSQL("INSERT INTO items (name,description) VALUES ('Дубина', 'Чтобы крушить');");
        database.execSQL("INSERT INTO items (name,description) VALUES ('Котлетки', 'Чтобы кушать');");
    }
    public void GiveItemToHero(int id_item,int id_hero)
    {
        database.execSQL("INSERT INTO heroesitems (id_item,id_hero) VALUES ("+ String.valueOf(id_item) +", "+String.valueOf(id_hero)+");");
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
