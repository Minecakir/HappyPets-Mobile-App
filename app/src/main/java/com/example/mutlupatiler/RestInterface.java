package com.example.mutlupatiler;
import com.example.mutlupatiler.Model.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestInterface {

    @GET("api/Contact")
    Call<List<repo>> GetAll();

    @GET("api/Contact/{id}")
    Call <Animal> GetAnimal(@Path("id") String id);

    @POST("api/Contact")
    Call<Void> NewAnimal (@Body Animal animal);

    @PUT("api/Contact/{id}")
    Call<Void> Update (@Path("id") String id, @Body Animal animal );

    @DELETE("api/Contact/{id}")
    Call<Void> Delete (@Path("id") String id);
}

