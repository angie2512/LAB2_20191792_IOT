package com.example.lab2_20191792;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ContadorWorker extends Worker {
    public ContadorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int contador = getInputData().getInt("number",0);
        int contadorFinal = contador + 122;

        while (contador <= contadorFinal) {
            Log.d("msg-test", "contador: " + contador);
            setProgressAsync(new Data.Builder().putInt("contador",contador).build());
            contador++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Result.failure();
            }
        }
        Data data = new Data.Builder()
                .putString("info","Worker finalizado")
                .build();
        return Result.success(data);
    }
}
