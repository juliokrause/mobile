package com.pucpr.cameraapp.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }
    private ListDatabase database;
    private ArrayList<List> Lists;
    private Context context;

    public void setContext(Context context){

        this.context = context;
        database = new ListDatabase(context);

        Lists = database.RetrieveListsFromDB();


        //addList(new List("Rio de Janeiro",6747815));
        //addList(new List("São Paulo",12325232));



    }

    public ArrayList<List> getLists(){
        return Lists;
    }

    public void removeList(){
        long id = database.deleteColecaoInALL();
    }

    public void addList(List List){
        long id = database.createListInDB(List);
        if(id > 0){
            List.setId(id);
            Lists.add(List);
        }else{
            Toast.makeText(
                    context,"Add List problem",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void updateList(List List){
        long i = database.updateCityInDB(List);
        if(i > 0){
            Lists.remove(List);
            Lists.add(List);
        }else{
            Toast.makeText(context,"Update list problem", Toast.LENGTH_LONG).show();
        }
    }


}
