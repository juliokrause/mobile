package com.pucpr.cameraapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pucpr.cameraapp.R;
import com.pucpr.cameraapp.model.DataModel;

public class RecycleView extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    FloatingActionButton FloatingBGoToAddView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recyclerView = findViewById(R.id.recyclerView);
        DataModel.getInstance().setContext(RecycleView.this);
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new GridLayoutManager(RecycleView.this, 2)
        );

        FloatingBGoToAddView = findViewById(R.id.FloatingBGoToAddView);

        FloatingBGoToAddView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gotoAddView(view);
            }
        });


        adapter.setClickListener(new ListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                gotoProductEdit(view, position);

            };

            @Override
            public boolean onItemLongClick(int position, View view) {
                DataModel.getInstance().getLists().remove(position);
                adapter.notifyItemRemoved(position);
                return true;
            };


        });


    }



    public void gotoAddView(View view) {
        Intent intent = new Intent(RecycleView.this, AddActivity.class);

        startActivity(intent);

    }


    public void gotoProductEdit(View view, int position) {
        Intent intent = new Intent(RecycleView.this, EditItemActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }


}