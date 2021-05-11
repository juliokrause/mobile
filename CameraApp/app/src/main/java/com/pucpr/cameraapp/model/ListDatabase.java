package com.pucpr.cameraapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.pucpr.cameraapp.model.List;

import java.util.ArrayList;

public class ListDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Lists.sqlsql";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "List";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_POPULATION = "population";
    private static final String COL_PATH = "path";
    private static final String COL_RATING = "rating";




    /*
    CREATE
    RETRIEVE
    UPDATE
    DELETE
    CRUD
     */

    private Context context;

    public ListDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query;/* = "CREATE TABLE IF NOT EXISTS "+DB_TABLE+"("+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NAME + " TEXT, "+  COL_POPULATION + " INTEGER, "+ COL_PATH + " TEXT, "+  COL_RATING + " INTEGER ");";*/
        query = String.format("CREATE TABLE IF NOT EXISTS %s("+
                " %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " %s TEXT, " +
                " %s INTEGER," +
                " %s TEXT," +
                " %s INTEGER)",DB_TABLE,COL_ID,COL_NAME,COL_POPULATION,COL_PATH,COL_RATING);

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //CRUD
    //CREATE - CRIA UMA List NO BD
    public long createListInDB(List List){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,List.getName());
        values.put(COL_POPULATION,List.getvalorm());
        values.put(COL_PATH, List.getPath());
        values.put(COL_RATING, List.getRating());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }


    //RETRIEVE - TRAZER OS DADOS DO BD
    public ArrayList<List> RetrieveListsFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE,null,null,
                null,null,null,null);
        ArrayList<List> Lists = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                int population = cursor.getInt(cursor.getColumnIndex(COL_POPULATION));
                String path = cursor.getString(cursor.getColumnIndex(COL_PATH));
                int rating = (cursor.getColumnIndex(COL_RATING));

                List c = new List(id,name,population, path, rating);
                Lists.add(c);

            }while (cursor.moveToNext());
        }
        database.close();
        return Lists;
    }

    public long deleteColecaoInDB(long id){
        SQLiteDatabase database = getWritableDatabase();
        long i = database.delete(DB_TABLE, COL_ID+" = ? ", new String[]{String.valueOf(id)});
        database.close();
        return  i;
    }




    public long updateCityInDB(List list){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, list.getName());
        values.put(COL_POPULATION, list.getvalorm());
        values.put(COL_RATING, list.getRating());
        values.put(COL_PATH, list.getPath());
        long i = database.update(DB_TABLE,values, COL_ID+" = ? ", new String[]{String.valueOf(list.getId())});
        database.close();
        return i;
    }


    public long deleteColecaoInALL(){
        SQLiteDatabase database = getWritableDatabase();
        long i = database.delete(DB_TABLE, null,null);
        database.close();
        return  i;
    }





}
