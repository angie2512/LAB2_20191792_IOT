package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(hayinternet()){
            Toast.makeText(this,"Success: Tiene internet", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Error: No tiene internet", Toast.LENGTH_LONG).show();
        }
    }

    public void abrirOtraActivity(View view){
        Intent intent = new Intent(this,IniciodesesionActivity.class);
        startActivity(intent);


    }

    public boolean hayinternet(){
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test","Internet: " + tieneInternet);
        return tieneInternet;
    }
}