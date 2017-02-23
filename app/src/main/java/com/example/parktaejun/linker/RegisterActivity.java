package com.example.parktaejun.linker;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    TextView id;
    TextView pw;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Font.setGlobalFont(this, getWindow().getDecorView());

        id = (TextView)findViewById(R.id.id);
        pw = (TextView)findViewById(R.id.pw);
        register = (Button)findViewById(R.id.register);

        int color = Color.parseColor("#319A7E");
        id.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        pw.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View toolbar_view = LayoutInflater.from(this).inflate(R.layout.toolbar, null);
        TextView title = (TextView) toolbar_view.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar_view.findViewById(R.id.back);
        title.setText("회원가입");
        getSupportActionBar().setCustomView(toolbar_view);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "click");
                finish();
            }
        });

    }
}
