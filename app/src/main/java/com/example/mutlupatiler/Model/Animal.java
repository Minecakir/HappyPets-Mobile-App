package com.example.mutlupatiler.Model;

public class Animal {
    private Integer id;
    private String name;
    private String  temperament;
    private String  type;
    private String  imgUrl;
    private int  age;

    private String gender;
    private String barren;
    private String personName;
    private String personSurname;
    private String city;
    private String username;

    public Animal(String name, String temperament, String type, String imgUrl,String gender,String barren,String personName,String personSurname,String city, String username, int age) {
        this.name = name;
        this.temperament = temperament;
        this.type = type;
        this.imgUrl = imgUrl;
        this.age = age;
        this.gender = gender;
        this.barren = barren;
        this.personName = personName;
        this.personSurname = personSurname;
        this.city = city;
        this.username = username;
    }

    public Animal(Integer id,String name, String temperament, String type, String imgUrl,String gender,String barren,String personName,String personSurname,String city, String username, int age) {
        this.id = id;
        this.name = name;
        this.temperament = temperament;
        this.type = type;
        this.imgUrl = imgUrl;
        this.age = age;
        this.gender = gender;
        this.barren = barren;
        this.personName = personName;
        this.personSurname = personSurname;
        this.city = city;
        this.username = username;
    }

    public Animal(String name, String temperament, String type, String imgUrl,String gender,String barren,String personName,String personSurname,String city, int age) {
        this.name = name;
        this.temperament = temperament;
        this.type = type;
        this.imgUrl = imgUrl;
        this.age = age;
        this.gender = gender;
        this.barren = barren;
        this.personName = personName;
        this.personSurname = personSurname;
        this.city = city;
    }

    public Animal(String name, String temperament, String type, String imgUrl, int age) {
        this.name = name;
        this.temperament = temperament;
        this.type = type;
        this.imgUrl = imgUrl;
        this.age = age;
    }

    public Animal(String name, String imgUrl, int age) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBarren() {
        return barren;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBarren(String barren) {
        this.barren = barren;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
