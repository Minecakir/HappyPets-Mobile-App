package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mutlupatiler.API.ApiClient;
import com.example.mutlupatiler.Model.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePage extends AppCompatActivity {
    //REST INTERFACE
    RestInterface restInterface;

    //PET
    String petID,username, pet_name,pet_temp,person_name,person_surname,person_city,type,gender,img_url,barren;
    Integer pet_age;

    //EDIT TEXTS
    EditText petName, petTemp,petCity,petAge;
    //RadioButton
    RadioButton rbYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        restInterface = ApiClient.getClient().create(RestInterface.class);

        //FIND BY ID
        petName = findViewById(R.id.editTextUpdateName);
        petTemp = findViewById(R.id.editTextUpdateTemp);
        petCity = findViewById(R.id.editTextUpdateCity);
        petAge = findViewById(R.id.editTextUpdateAge);
        rbYes = findViewById(R.id.radioButtonUpdateBarren);

        //GET DATA FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        petID= extra.getString("petID");
        username = extra.getString("username");
        pet_name = extra.getString("petName");
        pet_temp = extra.getString("petTemp");
        person_name = extra.getString("personName");
        person_surname = extra.getString("personSurname");
        person_city = extra.getString("city");
        type = extra.getString("petType");
        gender = extra.getString("petGender");
        img_url = extra.getString("imgUrl");
        barren = extra.getString("petBarren");
        pet_age = Integer.valueOf(extra.getString("petAge"));

        petName.setText(pet_name);
        petTemp.setText(pet_temp);
        petCity.setText(person_city);
    }

    public void UpdatePet(View view) {

        pet_name = petName.getText().toString();
        pet_temp = petTemp.getText().toString();
        person_city = petCity.getText().toString();
        if(Integer.valueOf(petAge.getText().toString())!=0){
            pet_age = Integer.valueOf(petAge.getText().toString());
        }

        Animal updatedAnimal = new Animal(pet_name,pet_temp,type,img_url,gender,barren,person_name,person_surname,person_city,username,pet_age);

        Call<Void> call;
        call = restInterface.Update(petID, updatedAnimal);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200 || response.code() == 204) {
                    Toast.makeText(UpdatePage.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdatePage.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdatePage.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setYes(View view) {
        RadioButton rbTemp =(RadioButton) view;

        if(rbTemp.getId()==rbYes.getId())
        {
            Toast.makeText(this,"Evet Seçildi.",Toast.LENGTH_SHORT).show();
            barren = "Evet";
        }
        else{
            barren = barren;
        }
    }
}