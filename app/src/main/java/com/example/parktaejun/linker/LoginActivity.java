package com.example.parktaejun.linker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Font.setGlobalFont(this, getWindow().getDecorView());

        Intent SplashIntent = new Intent(LoginActivity.this, SplashActivity.class);
        startActivity(SplashIntent);
    }
}
