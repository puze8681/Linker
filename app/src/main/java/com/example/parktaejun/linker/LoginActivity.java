package com.example.parktaejun.linker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView id;
    TextView pw;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Font.setGlobalFont(this, getWindow().getDecorView());

        id = (TextView)findViewById(R.id.id);
        pw = (TextView)findViewById(R.id.pw);
        login = (Button)findViewById(R.id.login);
        register = (TextView)findViewById(R.id.register);

        int color = Color.parseColor("#ffffff");
        id.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        pw.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}
