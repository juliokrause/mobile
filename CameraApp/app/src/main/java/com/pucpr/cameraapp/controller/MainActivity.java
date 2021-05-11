package com.pucpr.cameraapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pucpr.cameraapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnGoToCamera;
    Button btnGoToRecycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoToCamera = findViewById(R.id.btnGoToCamera);
        btnGoToRecycleView = findViewById(R.id.btnGoToRecycleView);

        btnGoToCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goToCamera(view);
            }
        });

        btnGoToRecycleView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goToListing(view);
            }
        });




    }

        public void goToCamera(View view) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        }

        public void goToListing(View view) {
            Intent intent = new Intent(MainActivity.this, RecycleView.class);
            startActivity(intent);
        }


}