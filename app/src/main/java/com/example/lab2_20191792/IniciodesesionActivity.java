package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class IniciodesesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciodesesion);
        Toast.makeText(this, "Actividad Actual: " + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();


    }

    public void abrirOtraActivity2(View view){
        Intent intent = new Intent(this,Opciones.class);
        startActivity(intent);
    }
}