package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
    }

    public void abrirOtraActivity3(View view){
        Intent intent = new Intent(this,ContadorActivity.class);
        startActivity(intent);
    }

    public void abrirOtraActivity4(View view){
        Intent intent = new Intent(this,CronometroActivity.class);
        startActivity(intent);
    }
}