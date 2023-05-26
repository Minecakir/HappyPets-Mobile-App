package com.example.mutlupatiler.Adapter;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mutlupatiler.Model.Animal;
import com.example.mutlupatiler.R;

import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private final int GALLERY_REQ_CODE = 1000;
    List<Animal> animalList;
    LayoutInflater userInflater;

    public AnimalAdapter (Activity activity, List<Animal> animalList){
        this.animalList = animalList;
        userInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int i) {
        return animalList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = userInflater.inflate(R.layout.satir,null);

        //FIND VIEW BY ID
        TextView lblName = lineView.findViewById(R.id.textViewName);
        ImageView img = lineView.findViewById(R.id.imageView);

        Animal tmpAnimal = animalList.get(i);

        //SET ANIMAL NAME
        lblName.setText(tmpAnimal.getName());

        try {
            Glide.with(userInflater.getContext()).load(tmpAnimal.getImgUrl()).error(R.drawable.paws).into(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineView;
    }
}