package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab2_20191792.dto.Person;
import com.squareup.picasso.Picasso;

public class Opciones extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Toast.makeText(this, "Current Activity: " + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");
        assert person != null;
        Log.d("msg-test", "Name: " + person.getResults().get(0).getName().getFirst());

        TextView username = findViewById(R.id.username);
        TextView nombre = findViewById(R.id.nombreapellido);
        username.setText(person.getResults().get(0).getLogin().getUsername());
        nombre.setText(person.getResults().get(0).getName().getFirst() + " " +  person.getResults().get(0).getName().getLast());


        ImageView imageView = findViewById(R.id.profile);
        Picasso.get()
                .load(person.getResults().get(0).getPicture().getLarge())
                .resize(300, 300)
                .error(R.drawable.logogoogle)
                .into(imageView);

        Button cronometro = findViewById(R.id.button3);
        cronometro.setOnClickListener(view ->{
            Intent crono = new Intent(Opciones.this, CronometroActivity.class);
            startActivity(crono);
        });
        Button contador = findViewById(R.id.button4);
        contador.setOnClickListener(view -> {
            Intent conta = new Intent(Opciones.this, ContadorActivity.class);
            startActivity(conta);
        });
    }
}