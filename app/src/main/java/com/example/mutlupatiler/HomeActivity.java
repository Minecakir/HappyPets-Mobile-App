package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    ImageView iconAdopting;
    ImageView iconAdoptYourPet;
    ImageView iconMyPets;

    //USERNAME
    String username;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //SET ICON
        iconAdopting = findViewById(R.id.imageViewAdopt);
        iconAdoptYourPet =  findViewById(R.id.imageView2);
        iconMyPets =  findViewById(R.id.imageView3);

        iconAdopting.setImageResource(R.drawable.adopting_icon);
        iconAdoptYourPet.setImageResource(R.drawable.adoptyourpet);
        iconMyPets.setImageResource(R.drawable.cat);


        //GET DATA FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        username=extra.getString("UserName");

        // bmImg = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/IMG_20230101_231635.jpg");
        //content://media/external/images/media/32

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

    }

    public void AdoptingPage(View view) {
        Intent intent = new Intent(this, AnimalList.class);
        // DATA TO ANOTHER PAGE
        intent.putExtra("UserName",username);
        startActivity(intent);
    }

    public void AdoptYourPet(View view) {
        Intent intent = new Intent(this, AdoptYourPet.class);
        // DATA TO ANOTHER PAGE
        intent.putExtra("UserName",username);
        startActivity(intent);
    }

    public void MyPetsPage(View view) {
        Intent intent = new Intent(this, MyPets.class);
        // DATA TO ANOTHER PAGE
        intent.putExtra("UserName",username);
        startActivity(intent);
    }
}