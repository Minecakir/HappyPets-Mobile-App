package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mutlupatiler.API.ApiClient;
import com.example.mutlupatiler.Adapter.AnimalAdapter;
import com.example.mutlupatiler.Model.Animal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalList extends AppCompatActivity {

    RestInterface restInterface;
    //TextView getAnimalName;
    //ImageView imgAnimal;

    ImageView paws;

    ListView lv;
    List<Animal> animalArrayList;
    Spinner sp;

    //USERNAME
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        //GET USERNAME FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        username=extra.getString("UserName");

        //SET IMAGE PAWS
        paws = findViewById(R.id.imageViewPaws);
        paws.setImageResource(R.drawable.paws);

        /*SPINNER*/
        List<String> types = new ArrayList<>();
        sp = findViewById(R.id.spinner);

        /*Add Types In Type List*/
        types.add("Kedi&Köpek");
        types.add("Kedi");
        types.add("Köpek");

        //ARRAY ADAPTER FOR SPINNER
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, types);
        sp.setAdapter(arrayAdapter);

        //getAnimalName = findViewById(R.id.textViewGetAnimal);
        //imgAnimal = findViewById(R.id.imageViewAnimal);
        lv = findViewById(R.id.listViewAnimal);
        animalArrayList = new ArrayList<>();

        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<List<repo>> call = restInterface.GetAll();

        call.enqueue(new Callback<List<repo>>() {
            @Override
            public void onResponse(Call<List<repo>> call, Response<List<repo>> response) {
                if (response.code() == 200) {
                    // Success
                    Toast.makeText(AnimalList.this, "Success", Toast.LENGTH_SHORT).show();

                    List<repo> reposList = response.body();

                    for (int i = 0; i < reposList.size(); i++) {
                        animalArrayList.add(new Animal(reposList.get(i).id,reposList.get(i).name, reposList.get(i).temperament, reposList.get(i).type, reposList.get(i).imgUrl, reposList.get(i).gender,reposList.get(i).barren,reposList.get(i).personName,reposList.get(i).personSurname,reposList.get(i).city, reposList.get(i).username, reposList.get(i).age));
                    }
                    AnimalAdapter adapter = new AnimalAdapter(AnimalList.this, animalArrayList);
                    lv.setAdapter(adapter);

                    sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (types.get(i).equals("Kedi")) {
                                animalArrayList.clear();
                                for (int j = 0; j < reposList.size(); j++) {
                                    if (reposList.get(j).type.equals("Cat")) {
                                        animalArrayList.add(new Animal(reposList.get(j).id,reposList.get(j).name, reposList.get(j).temperament, reposList.get(j).type, reposList.get(j).imgUrl, reposList.get(j).gender,reposList.get(j).barren,reposList.get(j).personName,reposList.get(j).personSurname,reposList.get(j).city, reposList.get(j).username, reposList.get(j).age));
                                    }
                                }
                                AnimalAdapter adapter = new AnimalAdapter(AnimalList.this, animalArrayList);
                                lv.setAdapter(adapter);
                            } else if (types.get(i).equals("Köpek")) {
                                animalArrayList.clear();
                                for (int j = 0; j < reposList.size(); j++) {
                                    if (reposList.get(j).type.equals("Dog")) {
                                        animalArrayList.add(new Animal(reposList.get(j).id,reposList.get(j).name, reposList.get(j).temperament, reposList.get(j).type, reposList.get(j).imgUrl, reposList.get(j).gender,reposList.get(j).barren,reposList.get(j).personName,reposList.get(j).personSurname,reposList.get(j).city, reposList.get(j).username, reposList.get(j).age));
                                    }
                                }
                                AnimalAdapter adapter = new AnimalAdapter(AnimalList.this, animalArrayList);
                                lv.setAdapter(adapter);
                            } else {
                                animalArrayList.clear();
                                for (int j = 0; j < reposList.size(); j++) {
                                    animalArrayList.add(new Animal(reposList.get(j).id,reposList.get(j).name, reposList.get(j).temperament, reposList.get(j).type, reposList.get(j).imgUrl, reposList.get(j).gender,reposList.get(j).barren,reposList.get(j).personName,reposList.get(j).personSurname,reposList.get(j).city, reposList.get(j).username, reposList.get(j).age));
                                }
                                AnimalAdapter adapter = new AnimalAdapter(AnimalList.this, animalArrayList);
                                lv.setAdapter(adapter);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    //Toast.makeText(AnimalList.this, "Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AnimalList.this, "Connection Error", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<List<repo>> call, Throwable t) {
                Toast.makeText(AnimalList.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });

        //LISTVIEW CHOOSE
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal tmp = (Animal) adapterView.getAdapter().getItem(i);
                //Toast.makeText(AnimalList.this, tmp.getName(), Toast.LENGTH_SHORT).show();

                //INTENT
                Intent intent = new Intent(getApplicationContext(), DetailAnimal.class);

                // VERI AKTARIMI
                intent.putExtra("PetId",tmp.getId());
                intent.putExtra("PetName", tmp.getName());
                intent.putExtra("PetTemperament", tmp.getTemperament());
                intent.putExtra("PetImgUrl", tmp.getImgUrl());
                intent.putExtra("PetGender",tmp.getGender());
                intent.putExtra("PetBarren",tmp.getBarren());
                intent.putExtra("PetPersonName",tmp.getPersonName());
                intent.putExtra("PetPersonSurname",tmp.getPersonSurname());
                intent.putExtra("PetCity",tmp.getCity());
                intent.putExtra("PetAge",Integer.toString(tmp.getAge()));
                intent.putExtra("PetUserName",tmp.getUsername());
                intent.putExtra("username",username);

                startActivity(intent);
            }
        });

    }

    public void AddNewAnimal(View view) {
        Animal newAnimal = new Animal("Koko", "Friendly", "Dog", "https://images.unsplash.com/photo-1612502169027-5a379283f9c0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80", 0);

        //ADD NEW ANIMAL
        Call<Void> call;
        call = restInterface.NewAnimal(newAnimal);

        //CONTROL CONNECTION
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200 || response.code() == 204) {
                    Toast.makeText(AnimalList.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AnimalList.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AnimalList.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void GetAllAnimals(View view) {
        Call<List<repo>> call = restInterface.GetAll();
        call.enqueue(new Callback<List<repo>>() {
            @Override
            public void onResponse(Call<List<repo>> call, Response<List<repo>> response) {
                if (response.code() == 200) {
                    // Succes
                    List<repo> reposList = response.body();

                    for (int i = 0; i < reposList.size(); i++) {
                        System.out.println(String.valueOf(reposList.get((i)).name));
                        animalArrayList.add(new Animal(reposList.get(i).name, reposList.get(i).imgUrl, reposList.get(i).age));
                    }


                } else {
                    Toast.makeText(AnimalList.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<repo>> call, Throwable t) {
                Toast.makeText(AnimalList.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}