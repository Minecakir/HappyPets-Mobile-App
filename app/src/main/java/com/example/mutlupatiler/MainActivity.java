package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView dog;
    ImageView pati;
    ImageView woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DESCRIBE IMAGE
        dog  = findViewById(R.id.imageViewDog);
        pati = findViewById(R.id.imageViewPati);
        woman = findViewById(R.id.imageViewWoman);
        //SET IMAGES
        dog.setImageResource(R.drawable.dog);
        pati.setImageResource(R.drawable.paws);
        woman.setImageResource(R.drawable.woman_mainpage);
    }

    public void SignInPage(View view) {
        Intent intent = new Intent(this, signin.class);
        startActivity(intent);
    }

    public void GoRegisterPage(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}