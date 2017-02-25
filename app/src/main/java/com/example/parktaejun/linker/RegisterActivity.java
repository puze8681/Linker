package com.example.parktaejun.linker;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.parktaejun.linker.Server.JSONService;
import com.example.parktaejun.linker.Server.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    TextView id;
    TextView pw;
    TextView name;
    Button register;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Font.setGlobalFont(this, getWindow().getDecorView());
        //폰트 설정

        id = (TextView)findViewById(R.id.id);
        pw = (TextView)findViewById(R.id.pw);
        name = (TextView)findViewById(R.id.name);
        register = (Button)findViewById(R.id.register);

        int color = Color.parseColor("#319A7E");
        id.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        pw.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        name.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        //EditText 밑줄 색 바꾸는 코드

        retrofit = new Retrofit.Builder().baseUrl("http://nh.applepi.kr").addConverterFactory(GsonConverterFactory.create()).build();
        //레트로핏에 메인주소 연결

        final JSONService service = retrofit.create(JSONService.class);
        //JSONService 를 상속받은 service 변수 생성, 레트로핏과 연결

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View toolbar_view = LayoutInflater.from(this).inflate(R.layout.toolbar, null);
        TextView title = (TextView) toolbar_view.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar_view.findViewById(R.id.back);
        title.setText("회원가입");
        getSupportActionBar().setCustomView(toolbar_view);
        //툴바 설정 코드

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> call = service.register(id.getText().toString(), pw.getText().toString(), name.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code() == 200){
                            User user = response.body();
                            if(user != null){
                                Toast.makeText(getApplicationContext(), "회원가입 완료 ... ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else{
                            Toast.makeText(getApplicationContext(), "회원가입 실패 ... ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "요청 실패 ... ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
        //회원가입 서버 연동

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "click");
                finish();
            }
        });

    }
}
