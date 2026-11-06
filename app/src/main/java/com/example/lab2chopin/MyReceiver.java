package com.example.lab2chopin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Через 7 секунд после старта приложения Activity шлёт broadcast,
 * этот receiver ловит его и запускает сервис со Шопеном.
 */
public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION_PLAY_CHOPIN =
            "com.example.lab2chopin.ACTION_PLAY_CHOPIN";
    public static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }

        Log.d(TAG, "onReceive: " + intent.getAction());

        if (ACTION_PLAY_CHOPIN.equals(intent.getAction())) {
            Toast.makeText(context, "Receiver: запускаю Шопена", Toast.LENGTH_SHORT).show();
            Intent serviceIntent = new Intent(context, ChopinService.class);
            context.startService(serviceIntent);
            Log.d(TAG, "ChopinService started");
        }
    }
}
