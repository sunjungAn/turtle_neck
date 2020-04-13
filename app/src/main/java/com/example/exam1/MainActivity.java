package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button toast_btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast_btn = findViewById(R.id.toast_btn); //post btn의 주소값 가져옴
        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick method
                Toast toast1 = Toast.makeText(MainActivity.this, "허리up", Toast.LENGTH_LONG);
                //Toast라는 객체 makeText  6초정도
                toast1.show();//보여줘라
            }
        });
    }
}
