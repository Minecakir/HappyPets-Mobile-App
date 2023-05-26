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

public class MyPets extends AppCompatActivity {
    RestInterface restInterface;
    ListView listView;
    List<Animal> petList;
    //USERNAME
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        //LISTVIEW
        listView = findViewById(R.id.listViewMyPets);
        //PET LIST
        petList = new ArrayList<>();

        //GET DATA FROM ANOTHER PAGE -- //USERNAME
        Bundle extra=getIntent().getExtras();
        username=extra.getString("UserName");

        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<List<repo>> call = restInterface.GetAll();

        call.enqueue(new Callback<List<repo>>() {
            @Override
            public void onResponse(Call<List<repo>> call, Response<List<repo>> response) {
                if (response.code() == 200) {
                    // Success
                    Toast.makeText(MyPets.this, "Success", Toast.LENGTH_SHORT).show();

                    List<repo> reposList = response.body();

                    for (int i = 0; i < reposList.size(); i++) {
                        if(reposList.get(i).username.equals(username))
                        {
                            petList.add(new Animal(reposList.get(i).id,reposList.get(i).name, reposList.get(i).temperament, reposList.get(i).type, reposList.get(i).imgUrl, reposList.get(i).gender,reposList.get(i).barren,reposList.get(i).personName,reposList.get(i).personSurname,reposList.get(i).city, reposList.get(i).username, reposList.get(i).age));
                        }
                    }
                    //ADAPTER
                    AnimalAdapter adapter = new AnimalAdapter(MyPets.this, petList);
                    listView.setAdapter(adapter);

                    Toast.makeText(MyPets.this, "Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyPets.this, "Connection Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<repo>> call, Throwable t) {
                Toast.makeText(MyPets.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        });

        //LISTVIEW CHOOSE
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal tmp = (Animal) adapterView.getAdapter().getItem(i);

                //Toast.makeText(MyPets.this, tmp.getId(), Toast.LENGTH_SHORT).show();

                //INTENT
                Intent intent = new Intent(getApplicationContext(), DeletePage.class);

                // VERI AKTARIMI
                intent.putExtra("id",String.valueOf(tmp.getId()));
                intent.putExtra("username",username);
                System.out.println(tmp.getId());
                startActivity(intent);
            }
        });



    }
}