package com.pucpr.cameraapp.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pucpr.cameraapp.R;
import com.pucpr.cameraapp.model.DataModel;
import com.pucpr.cameraapp.model.List;

import java.io.File;
import java.nio.channels.SelectionKey;

public class EditItemActivity extends AppCompatActivity {

    EditText ProductPrice;
    EditText ProductName;
    TextView pn;
    TextView pp;
    String name;
    Integer price;
    SeekBar SeekBar;
    Button Confirm;
    int rating;
    ImageView imageView;
    String path;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);


        Confirm = findViewById(R.id.confirmEdit);
        pn = findViewById(R.id.textView8);
        pp = findViewById(R.id.textView9);
        ProductPrice = findViewById(R.id.editPrecoProduto);
        ProductName = findViewById(R.id.editNomeProduto);
        imageView = findViewById(R.id.imageView);

        SeekBar = findViewById(R.id.seekBar);



        Bundle extras = getIntent().getExtras();

        name = DataModel.getInstance().getLists().get(extras.getInt("position")).getName();
        price = DataModel.getInstance().getLists().get(extras.getInt("position")).getvalorm();
        rating = 0;
        rating = DataModel.getInstance().getLists().get(extras.getInt("position")).getRating();
        path = DataModel.getInstance().getLists().get(extras.getInt("position")).getPath();

        pn.setText(name);
        pp.setText(price.toString());

        if(!path.equals("NoPath")){

            imageView.setImageURI(null);

            File file = new File(path);

            imageView.setImageURI(Uri.fromFile(file));

        }

        ProductName.setText(name);
        ProductPrice.setText(price.toString());


        SeekBar.setMax(4);
        SeekBar.setProgress(rating);
        SeekBar.refreshDrawableState();

        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

                Toast.makeText(EditItemActivity.this, "Avaliação do item:" + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {



            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {


            }
        });

        SeekBar.setMax(4);
        SeekBar.setProgress(rating);
        SeekBar.refreshDrawableState();


        Confirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                List List = DataModel.getInstance().getLists().get(extras.getInt("position"));

                List.setName(ProductName.getText().toString());

                List.setRating(SeekBar.getProgress());

                List.setvalorm(Integer.valueOf(ProductPrice.getText().toString()));


                DataModel.getInstance().updateList(List);

                Toast.makeText(EditItemActivity.this, "Lista atualizada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);


            }
        });


    }

    public void goToCamera(View view) {
        Intent intent = new Intent(EditItemActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    public void goToGallery(View view){

        Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, 2);



    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 2){



            if(resultCode == RESULT_OK){

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);

                Bundle extras = getIntent().getExtras();

                List List = DataModel.getInstance().getLists().get(extras.getInt("position"));

                List.setPath(picturePath);

                DataModel.getInstance().updateList(List);

                c.close();

            }

        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!path.equals("NoPath")){

            imageView.setImageURI(null);

            File file = new File(path);

            imageView.setImageURI(Uri.fromFile(file));

        }

    }
}