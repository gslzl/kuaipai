package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class GuideActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 1000;  //延迟3秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                GuideActivity.this.startActivity(intent);
                GuideActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}

