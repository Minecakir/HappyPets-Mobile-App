package com.example.mutlupatiler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class repo {
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("temperament")
    @Expose
    public String temperament;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("imgUrl")
    @Expose
    public String imgUrl;

    @SerializedName("age")
    @Expose
    public int age;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("barren")
    @Expose
    public String barren;

    @SerializedName("personName")
    @Expose
    public String personName;

    @SerializedName("personSurname")
    @Expose
    public String personSurname;

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("username")
    @Expose
    public String username;
}