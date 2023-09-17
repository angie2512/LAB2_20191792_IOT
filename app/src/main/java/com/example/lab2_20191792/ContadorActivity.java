package com.example.lab2_20191792;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class ContadorActivity extends AppCompatActivity {

    private boolean inicio = true;
    private Integer diferencia;
    private long differenceInSeconds;
    UUID uuid = UUID.randomUUID();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);
        Toast.makeText(this, "Current Activity: " + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        Button button = findViewById(R.id.iniciarga);
        TextView textView = findViewById(R.id.textView6);
        String numStr = textView.getText().toString();
        int number = Integer.parseInt(numStr);
        button.setOnClickListener(view -> {
            if(inicio){
                inicio=false;
                Data dataBuilder = new Data.Builder()
                        .putInt("number", number)
                        .build();
                WorkRequest workRequest = new OneTimeWorkRequest.Builder(ContadorWorker.class)
                        .setId(uuid)
                        .setInputData(dataBuilder)
                        .build();
                WorkManager.getInstance(ContadorActivity.this.getApplicationContext())
                        .enqueue(workRequest);
            }else{
            }
        });
        WorkManager.getInstance(ContadorActivity.this.getApplicationContext())
                .getWorkInfoByIdLiveData(uuid)
                .observe(ContadorActivity.this, workInfo -> {
                    if(workInfo != null){
                        if(workInfo.getState() == WorkInfo.State.RUNNING){
                            Data progress = workInfo.getProgress();
                            int contador = progress.getInt("contador", 0);
                            Log.d("msg-test", "progress: " + contador);
                            textView.setText(String.format("%d", contador));
                        } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            Data outputData = workInfo.getOutputData();
                            String texto = outputData.getString("info");
                            assert texto != null;
                            Log.d("msg-test", texto);
                        }
                    }else{
                        Log.d("msg-test", "work info == null ");
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = findViewById(R.id.textView6);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String uuidString = preferences.getString("uuid", null);

        if (uuidString != null) {
            // Convert the stored UUID string back to a UUID object
            UUID storedUUID = UUID.fromString(uuidString);
            // Use the storedUUID as needed in your activity
            observe(textView,storedUUID);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uuid", uuid.toString()); // Store the UUID as a string
        editor.apply();
    }
    @Override
    public void onBackPressed() {
        // Call finish to stop the activity without destroying it
        finish();
    }

    private void observe(TextView textView, UUID uuid1){
        WorkManager.getInstance(ContadorActivity.this.getApplicationContext())
                .getWorkInfoByIdLiveData(uuid1)
                .observe(ContadorActivity.this, workInfo -> {
                    if(workInfo != null){
                        if(workInfo.getState() == WorkInfo.State.RUNNING){
                            Data progress = workInfo.getProgress();
                            int contador = progress.getInt("contador", 0);
                            Log.d("msg-test", "progress: " + contador);
                            textView.setText(String.format("%d", contador));
                        } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            Data outputData = workInfo.getOutputData();
                            String texto = outputData.getString("info");
                            assert texto != null;
                            Log.d("msg-test", texto);
                        }
                    }else{
                        Log.d("msg-test", "work info == null ");
                    }
                });
    }
}