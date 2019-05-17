package com.e.heroapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import HeroApi.HeroAPI;
import model.Heromodel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText Etname,Etdesc;
    Button Btnadd;
private String url="http://10.0.2.2:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Etname=findViewById(R.id.Etname);
        Etdesc=findViewById(R.id.Etdesc);

        Btnadd=findViewById(R.id.Btnadd);

        Btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


    }

    private void Register() {
        String name=Etname.getText().toString();
        String desc=Etdesc.getText().toString();

        Heromodel hero=new Heromodel(name,desc);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroAPI heroAPI=retrofit.create(HeroAPI.class);

        Call<Void> voidCall=heroAPI.registerHero(hero);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "You registered successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
