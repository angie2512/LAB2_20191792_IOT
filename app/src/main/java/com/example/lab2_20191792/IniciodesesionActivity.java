package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class IniciodesesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciodesesion);

    }

    public void abrirOtraActivity2(View view){
        Intent intent = new Intent(this,Opciones.class);
        startActivity(intent);
    }
}