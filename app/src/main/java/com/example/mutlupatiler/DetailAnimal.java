package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailAnimal extends AppCompatActivity {

    TextView lblName,lblTemp,lblAge,lblGender,lblBarren,lblPersonName,lblPersonSurname,lblCity;
    ImageView imageView;

    //PET ID
    Integer petID;

    //PET USERNAME - USERNAME
    String PetUserName,username;

    //ICON
    ImageView icon_paw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_animal);

        //FIND BY ID
        lblName = findViewById(R.id.textViewDetailName);
        lblAge = findViewById(R.id.textViewDetailAge);
        imageView = findViewById(R.id.imageViewDetailImage);
        lblGender = findViewById(R.id.textViewDetailGender);
        lblBarren = findViewById(R.id.textViewDetailBarren);
        lblPersonName = findViewById(R.id.textViewDetailPersonName);
        lblPersonSurname = findViewById(R.id.textViewDetailPersonSurname);
        lblCity = findViewById(R.id.textViewDetailCity);
        lblTemp = findViewById(R.id.textViewDetailTemp);
        //ICON
        icon_paw = findViewById(R.id.imageView4);

        //SET ICON
        icon_paw.setImageResource(R.drawable.paws);

        //GET DATA FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        String PetName=extra.getString("PetName");
        String PetTemp=extra.getString("PetTemperament");
        String PetAge=extra.getString("PetAge");
        String PetImgUrl=extra.getString("PetImgUrl");
        String PetGender = extra.getString("PetGender");
        String PetBarren = extra.getString("PetBarren");
        String PetPersonName = extra.getString("PetPersonName");
        String PetPersonSurname = extra.getString("PetPersonSurname");
        String PetCity = extra.getString("PetCity");
        PetUserName = extra.getString("PetUserName");
        username = extra.getString("username");

        petID = extra.getInt("PetId");
        //Toast.makeText(this, String.valueOf(petID), Toast.LENGTH_SHORT).show();

        //SET NAME-AGE-TEMPERAMENT
        lblName.setText(PetName);
        lblTemp.setText(PetTemp);
        lblAge.setText(PetAge);
        lblGender.setText(PetGender);
        lblBarren.setText(PetBarren);
        lblPersonName.setText(PetPersonName);
        lblPersonSurname.setText(PetPersonSurname);
        lblCity.setText(PetCity);

        //SET IMAGE
        Glide.with(this).load(PetImgUrl).into(imageView);
    }

    public void AdoptPage(View view) {
        if(PetUserName.equals(username)){
            Toast.makeText(this, "Bu zaten sizin evcil hayvanınız...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, AdoptPage.class);
            // VERI AKTARIMI
            intent.putExtra("petID",String.valueOf(petID));
            startActivity(intent);
        }

    }
}