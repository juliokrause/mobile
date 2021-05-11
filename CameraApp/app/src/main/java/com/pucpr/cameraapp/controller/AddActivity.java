package com.pucpr.cameraapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pucpr.cameraapp.R;
import com.pucpr.cameraapp.model.DataModel;
import com.pucpr.cameraapp.model.List;

public class AddActivity extends AppCompatActivity {

    Button addButton;
    EditText ProductName;
    EditText ProductPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addButton = findViewById(R.id.addProductButton);
        ProductName = findViewById(R.id.addNameEditText);
        ProductPrice = findViewById(R.id.addPriceEditText);


        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                DataModel.getInstance().addList(new List(ProductName.getText().toString(), Integer.valueOf(ProductPrice.getText().toString()), "NoPath", 0));

                AddActivity.super.onBackPressed();

            }
        });





    }
}