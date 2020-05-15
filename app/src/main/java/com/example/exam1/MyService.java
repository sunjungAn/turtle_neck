package com.example.exam1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MyService extends Service {
    private WindowManager.LayoutParams params;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() 호출됨.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨.");
        if(intent == null)
        {
            return Service.START_STICKY;
        }
        else{
            processCommand(intent);
        }
        //Intent showIntent = new Intent(getApplicationContext(), jiro.class);

        /*showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", "mike");
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        startActivity(showIntent);*/
        return super.onStartCommand(intent, flags, startId);


    }
    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d(TAG, "전달받은 데이터:"+command+","+name);
        try {
            Thread.sleep(5000);
        }catch(Exception e){}

        Intent ShowIntent = new Intent(getApplicationContext(), jiro.class);
        ShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                            Intent.FLAG_ACTIVITY_SINGLE_TOP|
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);

        ShowIntent.putExtra("command","show");
        ShowIntent.putExtra("name",name+"from service");
        startActivity(ShowIntent);
    }




    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
