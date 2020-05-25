package com.example.exam1;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class jiro extends AppCompatActivity implements SensorEventListener{ //SensorEventListener를 사용한다.

    Button toast_btn;
    TextView textView;
    Button execute;
    Button stretching;

    private SensorManager sensorManager; //객체 생성
    static int exe = 0;
    private final float[] accelerometerReading = new float[3]; //가속도
    private final float[] magnetometerReading = new float[3];
    private WindowManager.LayoutParams params;
    private float brightness;
    //


    private final float[] rotationMatrix = new float[9];  //회전값
    private final float[] orientationAngles = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED);
        toast_btn = findViewById(R.id.toast_btn);
        stretching = findViewById(R.id.stretching);
        stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(jiro.this, popup.class));
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        params = getWindow().getAttributes();
        execute = findViewById(R.id.execute);
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(exe == 0){
                    exe = 1;
                    Toast toast1 = Toast.makeText(jiro.this, "화면 밝기 기능이 시작됩니다.", Toast.LENGTH_LONG);
                    toast1.show();
                    Intent intent = new Intent(jiro.this, ForegroundService.class);
                    startService(intent);
                    execute.setText("중지");

                }
                else if(exe == 1){
                    exe = 0;
                    Toast toast1 = Toast.makeText(jiro.this, "화면 밝기 기능이 종료되었습니다.", Toast.LENGTH_LONG);
                    toast1.show();
                    stopService(new Intent(getApplicationContext(), MyService.class));
                    execute.setText("실행");
                }
            }
        });

        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(jiro.this, "허리up", Toast.LENGTH_LONG);
                toast1.show();
            }
        });

        if(exe == 1)
        {
            Intent intent = new Intent(getApplicationContext(), MyService.class);
            intent.putExtra("command", "show");
            intent.putExtra("name", "start");
            startService(intent);
        }
        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }
    @Override
    protected void onNewIntent(Intent intent){
        processCommand(intent);
        super.onNewIntent(intent);
    }
    private void processCommand(Intent intent)
    {
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            Toast.makeText(this, "서비스로부터 전달받은 데이터:"+command+","+name,Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {  //아직 사용X
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    @Override
    protected void onResume() { //아직 사용X
        super.onResume();
        // Get updates from the accelerometer and magnetometer at a constant rate.
        // To make batch operations more efficient and reduce power consumption,
        // provide support for delaying updates to the application.
        //
        // In this example, the sensor reporting delay is small enough such that
        // the application receives an update before the system checks the sensor
        // readings again.
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            sensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
    }
    @Override
    protected void onPause() { //아직 사용X
        super.onPause();


        // Don't receive any more updates from either sensor.
        sensorManager.unregisterListener(this);
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
            textView =  findViewById(R.id.textView);
            textView.setText("value_x"+anglexy+"\nvalue_y"+anglexz+"\nvalue_z"+ angleyz);
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

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
  /*  public void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(rotationMatrix, null,
                accelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(rotationMatrix, mOrientationAngles);

        // "mOrientationAngles" now has up-to-date information.
    }

*/
   public void changeScreenBrightness(int value) {
       Context context = getApplicationContext();
      // boolean canWrite = Settings.System.canWrite(context);
       if(true) {
           int sBrightness = (value * 225/255);
           Settings.System.putInt(context.getContentResolver(),
                   Settings.System.SCREEN_BRIGHTNESS_MODE,
                   Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
           Settings.System.putInt(context.getContentResolver(),
                   Settings.System.SCREEN_BRIGHTNESS, sBrightness);
       }
       else{
           Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
           context.startActivity(intent);
       }
      /*Window window = getWindow();
      WindowManager.LayoutParams layoutParams = window.getAttributes();
      layoutParams.screenBrightness = value * 1.0f/255;
      window.setAttributes(layoutParams);*/
  }



}


