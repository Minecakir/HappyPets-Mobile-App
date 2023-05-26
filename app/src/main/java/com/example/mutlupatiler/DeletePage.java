package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mutlupatiler.API.ApiClient;
import com.example.mutlupatiler.Model.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePage extends AppCompatActivity {

    //PET ID -- USERNAME -- Name -- Temp -- Type -- imgUrl -- Gender -- PersonName -- PersonSurname -- City
    String petID, username,petName,petTemp,petType,imgUrl,petGender, petBarren, personName,personSurname,city;
    //Pet Age
    String age;

    RestInterface restInterface3;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_page);

        restInterface3 = ApiClient.getClient().create(RestInterface.class);

        //IMAGE
        imageView = findViewById(R.id.imageViewPet);

        //GET DATA FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        petID= extra.getString("id");
        username = extra.getString("username");
        //Toast.makeText(DeletePage.this, petID, Toast.LENGTH_SHORT).show();

        Call<Animal> call;
        call = restInterface3.GetAnimal(petID);

        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.code() == 200 || response.code() == 204) {
                    Animal animal = response.body();
                    Glide.with(DeletePage.this).load(animal.getImgUrl()).error(R.drawable.paws).into(imageView);
                    petName = animal.getName();
                    petType = animal.getType();
                    petTemp = animal.getTemperament();
                    imgUrl = animal.getImgUrl();
                    age = String.valueOf(animal.getAge());
                    petGender = animal.getGender();
                    petBarren = animal.getBarren();
                    personName = animal.getPersonName();
                    personSurname = animal.getPersonSurname();
                    city = animal.getCity();
                    //getAnimalName.setText(animal.getName());
                    int das = 90;
                } else {
                    Toast.makeText(DeletePage.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Animal> call, Throwable t) {

            }
        });

    }

    //DELETE FROM API
    public void Delete(View view) {
        Call<Void> call;
        call = restInterface3.Delete(petID);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200 || response.code() == 204) {
                    Toast.makeText(DeletePage.this, "Succes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeletePage.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    //GO UPDATE PAGE
    public void UpdatePage(View view) {
        Intent intent = new Intent(this, UpdatePage.class);
        // VERI AKTARIMI
        System.out.println(petID);
        intent.putExtra("petID",petID);
        intent.putExtra("username",username);
        intent.putExtra("petName",petName);
        intent.putExtra("petType",petType);
        intent.putExtra("imgUrl",imgUrl);
        intent.putExtra("petAge",age);
        intent.putExtra("petGender",petGender);
        intent.putExtra("petBarren",petBarren);
        intent.putExtra("personName",personName);
        intent.putExtra("personSurname",personSurname);
        intent.putExtra("city",city);
        intent.putExtra("petTemp",petTemp);
        startActivity(intent);
    }
}