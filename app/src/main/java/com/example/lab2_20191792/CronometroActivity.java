package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class CronometroActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private boolean cero = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        Toast.makeText(this, "Current Activity: " + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        chronometer = findViewById(R.id.hora);
        Button iniciar = findViewById(R.id.iniciar);
        Button pausar = findViewById(R.id.pausar);
        Button limpiar = findViewById(R.id.limpiar);

        iniciar.setOnClickListener(view -> {
            if(!running && cero){
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                chronometer.start();
                running = true;
                cero = false;
            }
        });

        pausar.setOnClickListener(view -> {
            if(running && !cero){
                chronometer.stop();
                pauseOffset = SystemClock.elapsedRealtime()-chronometer.getBase();
                running = false;
            }
        });



        limpiar.setOnClickListener(view -> {
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
            cero = true;
        });
    }
}