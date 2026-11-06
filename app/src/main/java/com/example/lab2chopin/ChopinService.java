package com.example.lab2chopin;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/** Сервис проигрывания Шопена (файл res/raw/chopin). */
public class ChopinService extends Service {

    public static final String TAG = "ChopinService";
    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service Created");
        Toast.makeText(this, "ChopinService Created", Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create(this, R.raw.chopin);
        if (player != null) {
            player.setLooping(false);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Playing Chopin");
        Toast.makeText(this, "Играет Шопен", Toast.LENGTH_LONG).show();
        if (player != null) {
            player.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service Destroyed");
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
            player = null;
        }
        super.onDestroy();
    }
}
