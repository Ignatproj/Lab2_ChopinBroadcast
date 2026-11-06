package com.example.lab2chopin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final long DELAY_MS = 7000; // 7 секунд

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable playChopinRunnable;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvStatus.setText("Ожидание 7 секунд…");

        // Через 7 секунд после запуска — отправляем broadcast
        playChopinRunnable = new Runnable() {
            @Override
            public void run() {
                tvStatus.setText("Отправляю Broadcast → Шопен");
                Toast.makeText(MainActivity.this, "7 сек прошло — broadcast", Toast.LENGTH_SHORT).show();
                sendPlayChopinBroadcast();
            }
        };
        handler.postDelayed(playChopinRunnable, DELAY_MS);
        Log.d(TAG, "Запланирован broadcast через 7 секунд");
    }

    private void sendPlayChopinBroadcast() {
        Intent intent = new Intent(MyReceiver.ACTION_PLAY_CHOPIN);
        intent.setPackage(getPackageName());
        Log.d(TAG, "sendBroadcast ACTION_PLAY_CHOPIN");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(playChopinRunnable);
        super.onDestroy();
    }
}
