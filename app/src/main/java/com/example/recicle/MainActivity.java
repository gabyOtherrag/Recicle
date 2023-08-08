package com.example.recicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                finish();
                Intent intent = new Intent(MainActivity.this, FormLogin.class);
                startActivity(intent);
            }

        }, 4000);



    }
}