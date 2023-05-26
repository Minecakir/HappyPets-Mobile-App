package com.example.mutlupatiler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mutlupatiler.DB.DBHelper;

public class signin extends AppCompatActivity {

    ImageView imgLogin;
    EditText username, password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //DESCRIBE IMAGE
        imgLogin  = findViewById(R.id.imageViewSignIn);

        //SETIMAGE
        imgLogin.setImageResource(R.drawable.dog_signin);

        username = findViewById(R.id.editTextSignInUserName);
        password = findViewById(R.id.editTextSignInPassword);
        btnlogin = findViewById(R.id.buttonStart);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(signin.this, "Lütfen Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(signin.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        // VERI AKTARIMI
                        intent.putExtra("UserName",user);
                        //---//
                        startActivity(intent);
                    }else{
                        Toast.makeText(signin.this, "Böyle Bir Kullanıcı Bulunmamakta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}