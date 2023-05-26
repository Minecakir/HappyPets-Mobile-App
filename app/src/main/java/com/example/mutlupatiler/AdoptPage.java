package com.example.mutlupatiler;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mutlupatiler.API.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdoptPage extends AppCompatActivity {
    //REST
    RestInterface restInterface;

    EditText editTextPhone;

    //PETID
    String petID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_page);

        //REST
        restInterface = ApiClient.getClient().create(RestInterface.class);

        //PHONE
        editTextPhone = findViewById(R.id.editTextPhone);

        //GET DATA FROM ANOTHER PAGE
        Bundle extra=getIntent().getExtras();
        petID = extra.getString("petID");
        //Toast.makeText(this, petID, Toast.LENGTH_SHORT).show();
    }

    public void Adopt(View view) {
        if(editTextPhone.getText().length() >= 11)
        {
            Toast.makeText(this, "Tebrikler", Toast.LENGTH_SHORT).show();
            sendSms(view);

            //DELETE PET FROM API
            Call<Void> call;
            call = restInterface.Delete(petID);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200 || response.code() == 204) {
                        Toast.makeText(AdoptPage.this, "Succes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdoptPage.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });
            Intent intent = new Intent(this, LastPage.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Lütfen 11 haneli telefon numaranızı giriniz!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSms(View view){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            // use this checkSelfPermission method to check whether this permission is already granted or not
            int smsPermission= checkSelfPermission(Manifest.permission.SEND_SMS);
            if (smsPermission!= PackageManager.PERMISSION_GRANTED)
            {
                // sms permission is not granted then invoke the user to allow this permission
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},0);
            }
            else if (smsPermission== PackageManager.PERMISSION_GRANTED)
            {
                // if this permission is granted then call sms sending method
                smsSendingText();
            }
        }
        else
        {
            // Below Marshmallow android version no need to handle run time permission so call sms sending method
            smsSendingText();
        }
    }

    public void smsSendingText()
    {
        SmsManager smsManager = SmsManager.getDefault();
        String telNo = editTextPhone.getText().toString();
        smsManager.sendTextMessage(telNo, null,"Merhaba. Talebin bize ulaştı. Sizinle iletişime geçeceğiz"
                 , null, null);
    }
}