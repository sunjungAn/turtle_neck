package com.example.exam1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class jiro extends AppCompatActivity implements SensorEventListener{ //SensorEventListener를 사용한다.

    Button toast_btn;
    TextView textView;

    private SensorManager sensorManager; //객체 생성
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
        toast_btn = findViewById(R.id.toast_btn);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        params = getWindow().getAttributes();


        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(jiro.this, "허리up", Toast.LENGTH_LONG);
                toast1.show();
            }
        });


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
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);//각도가 움직일때 마다 각을 바꿔준다.
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
            if(angleyz <= 50)
                changeScreenBrightness(80);
            else changeScreenBrightness(-1);
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
  private void changeScreenBrightness(int value) {
      Window window = getWindow();
      WindowManager.LayoutParams layoutParams = window.getAttributes();
      layoutParams.screenBrightness = value * 1.0f/255;
      window.setAttributes(layoutParams);
  }
}


