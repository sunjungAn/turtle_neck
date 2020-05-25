package com.example.exam1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.zip.Inflater;

import static com.example.exam1.jiro.exe;

public class ForegroundService extends Service implements SensorEventListener {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    jiro jiro = new jiro();
    WindowManager.LayoutParams params= new WindowManager.LayoutParams();
    Handler handler = new Handler();

    @Override
    public void onCreate() {
       // Intent in = new Intent(this,jiro.class); //it  working...
        /// start new Activity
        //in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //this.startActivity(in);
      //  super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
   public void changeScreenBrightness(int value) {
        Context context = getApplicationContext();
        boolean canWrite = Settings.System.canWrite(context);
        if (canWrite) {
            int sBrightness = (value * 225 / 255);
            android.provider.Settings.System.putInt(context.getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                    android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            android.provider.Settings.System.putInt(context.getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS, sBrightness);
        } else {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            context.startActivity(intent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {  //아직 사용X
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }


    // Get readings from accelerometer and magnetometer. To simplify calculations,
    // consider storing these readings as unit vectors.
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Intent intent = new Intent(getApplicationContext(), MyService.class);
        // startService(intent);//각도가 움직일때 마다 각을 바꿔준다.
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float ax, ay, az, anglexy, anglexz, angleyz;

        ax = x;
        ay = y;
        az = z;

        anglexy = (float)(Math.atan2(ax, ay) / (Math.PI / 180));  //radian으로 바꿔준다.
        anglexz = (float)(Math.atan2(ax, az) / (Math.PI / 180));
        angleyz = (float)(Math.atan2(ay, az) / (Math.PI / 180));

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (exe == 1){
                if(80<=anglexy && anglexy<=100)
                    if(anglexz<=50)
                        changeScreenBrightness(-1);
                    else changeScreenBrightness(100);
                else{
                    if(angleyz <= 50)
                        changeScreenBrightness(-1);
                    else changeScreenBrightness(100);
                }
            }

        }
    }
    public void init() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.background, null);

        WindowManager wm = (WindowManager)getSystemService( Context.WINDOW_SERVICE );
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
        params= WindowManager.LayoutParams();
    }


}
