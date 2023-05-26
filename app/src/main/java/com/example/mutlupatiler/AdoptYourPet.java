package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutlupatiler.API.ApiClient;
import com.example.mutlupatiler.Model.Animal;

import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdoptYourPet extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    RestInterface restInterface2;

    //USERNAME
    String username;

    //STRING TYPE -- Radio Button
    String type;
    //STRING GENDER -- Radio Button
    String gender;
    //STRING BARREN -- Radio Button
    String barren;
    //STRING PET NAME
    String pet_name;
    //INT  PET AGE
    Integer pet_age;
    //STRING PET TEMPERAMENT
    String pet_temp;
    //STRING PERSON NAME
    String person_name;
    //STRING PERSON SURNAME
    String person_surname;
    //STRING PERSON CITY
    String person_city;
    //STRING IMAGE URL
    String img_url;

    //RADIO BUTTONS
    RadioButton rbDog;
    RadioButton rbCat;
    RadioButton rbBoy;
    RadioButton rbGirl;
    RadioButton rbBarren;
    RadioButton rbNotBarren;

    ImageView img;
    Button btn;
    Button add;
    String url;

    //PET NAME
    EditText petName;
    //PET AGE
    EditText petAge;
    //PET Temperament
    EditText petTemp;
    //PERSON NAME
    EditText personName;
    //PERSON SURNAME
    EditText personSurname;
    //PERSON CITY
    EditText personCity;
    //IMG URL
    EditText imgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_your_pet);

        img = findViewById(R.id.imageViewAdoptYourPet);
        btn = findViewById(R.id.buttonUpload);
        add = findViewById(R.id.button2);

        //RADIO BUTTONS
        rbCat = findViewById(R.id.radioButtonCat);
        rbDog = findViewById(R.id.radioButtonDog);
        rbBoy = findViewById(R.id.radioButtonErkek);
        rbGirl = findViewById(R.id.radioButtonDişi);
        rbBarren = findViewById(R.id.radioButtonBarren);
        rbNotBarren = findViewById(R.id.radioButtonNotBarren);

        //Edit Text
        petName = findViewById(R.id.editTextPetName);
        petTemp = findViewById(R.id.editTextTemperament);
        petAge = findViewById(R.id.editTextPetAge);
        personName = findViewById(R.id.editTextPersonName);
        personSurname = findViewById(R.id.editTextPersonSurname);
        personCity = findViewById(R.id.editTextCity);
        imgUrl = findViewById(R.id.editTextImgUrl);


        //GET DATA FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        username=extra.getString("UserName");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                img.setImageURI(data.getData());
                url = data.getDataString();
                //url= getRealPathFromUri(this,data.getData());
                System.out.println(url);
            }
        }
    }

    public void Add(View view) {
        restInterface2 = ApiClient.getClient().create(RestInterface.class);

        pet_name = petName.getText().toString();
        pet_temp = petTemp.getText().toString();
        person_name = personName.getText().toString();
        person_surname = personSurname.getText().toString();
        person_city = personCity.getText().toString();
        pet_age = Integer.valueOf(petAge.getText().toString());

        if(url.isEmpty()){
            img_url = imgUrl.getText().toString();
        }
        else{
            img_url = url;
        }

            //ADD NEW ANIMAL
            Call<Void> call;
            call = restInterface2.NewAnimal(new Animal(pet_name,pet_temp,type,img_url,gender,barren,person_name,person_surname,person_city,username,pet_age));

            //CONTROL CONNECTION
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200 || response.code() == 204) {
                        Toast.makeText(AdoptYourPet.this, "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdoptYourPet.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(AdoptYourPet.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });

    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void type(View view) {
        RadioButton rbTemp =(RadioButton) view;

        if(rbTemp.getId()==rbCat.getId())
        {
            Toast.makeText(this,"Kedi Seçildi.",Toast.LENGTH_SHORT).show();
            type = "Cat";
        }
        else if(rbTemp.getId()==rbDog.getId()){
            type = "Dog";
            Toast.makeText(this,"Köpek Seçildi.",Toast.LENGTH_SHORT).show();
        }
    }

    public void gender(View view) {
        RadioButton rbTemp =(RadioButton) view;

        if(rbTemp.getId()==rbBoy.getId())
        {
            Toast.makeText(this,"Erkek Seçildi.",Toast.LENGTH_SHORT).show();
            gender = "Erkek";
        }
        else if(rbTemp.getId()==rbGirl.getId()){
            gender = "Disi";
            Toast.makeText(this,"Dişi Seçildi.",Toast.LENGTH_SHORT).show();
        }
    }

    public void barren(View view) {
        RadioButton rbTemp =(RadioButton) view;

        if(rbTemp.getId()==rbBarren.getId())
        {
            Toast.makeText(this,"Evet Seçildi.",Toast.LENGTH_SHORT).show();
            barren = "Evet";
        }
        else if(rbTemp.getId()==rbNotBarren.getId()){
            barren = "Hayir";
            Toast.makeText(this,"Hayir Seçildi.",Toast.LENGTH_SHORT).show();
        }
    }

}
