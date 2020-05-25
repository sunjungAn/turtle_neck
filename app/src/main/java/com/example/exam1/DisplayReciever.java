package com.example.exam1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class DisplayReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent in = new Intent(context, ForegroundService.class);
            context.startForegroundService(in);
        } else {
            Intent in = new Intent(context, ForegroundService.class);
            context.startService(in);
        }
    }
}

