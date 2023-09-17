package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.lab2_20191792.Services.Api;
import com.example.lab2_20191792.dto.Person;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IniciodesesionActivity extends AppCompatActivity {

    Api api;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciodesesion);

        Toast.makeText(this, "Current Activity: " + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        api = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
        fetchWebServiceData();

        Button createAccount = findViewById(R.id.button2);
        Button google = findViewById(R.id.button);
        CheckBox checkBox = findViewById(R.id.checkBox);
        createAccount.setOnClickListener(view -> {
            if(checkBox.isChecked()){
                Intent intent = new Intent(IniciodesesionActivity.this, Opciones.class);
                intent.putExtra("person", person);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_LONG).show();
            }
        });

    }
    public boolean hayInternet(){
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test","Internet: " + tieneInternet);
        return tieneInternet;
    }
    public void fetchWebServiceData(){
        TextInputEditText nombres = findViewById(R.id.nombres);
        TextInputEditText apellidos = findViewById(R.id.apellidos);
        TextInputEditText correo = findViewById(R.id.correo);
        TextInputEditText contrasenia = findViewById(R.id.contra);
        if(hayInternet()){
            api.getPerson().enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                    Log.d("msg-test", "Funciona");
                    if(response.isSuccessful()){
                        person = response.body();
                        assert person != null;
                        nombres.setText(person.getResults().get(0).getName().getFirst());
                        nombres.setFocusableInTouchMode(false);
                        nombres.setFocusable(false);
                        apellidos.setText(person.getResults().get(0).getName().getLast());
                        apellidos.setFocusableInTouchMode(false);
                        apellidos.setFocusable(false);
                        correo.setText(person.getResults().get(0).getEmail());
                        correo.setFocusableInTouchMode(false);
                        correo.setFocusable(false);
                        contrasenia.setText(person.getResults().get(0).getLogin().getPassword());
                        contrasenia.setFocusableInTouchMode(false);
                        contrasenia.setFocusable(false);
                    }
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {
                    Log.d("msg-test", "No funciona");
                }
            });
        }
    }
}