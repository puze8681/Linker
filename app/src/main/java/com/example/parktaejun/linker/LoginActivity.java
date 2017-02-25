package com.example.parktaejun.linker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaejun.linker.Server.JSONService;
import com.example.parktaejun.linker.Server.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextView id;
    TextView pw;
    Button login;
    TextView register;
    Retrofit retrofit;
    ProgressDialog progress_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Font.setGlobalFont(this, getWindow().getDecorView());
        //폰트 설정

        id = (TextView)findViewById(R.id.id);
        pw = (TextView)findViewById(R.id.pw);
        login = (Button)findViewById(R.id.login);
        register = (TextView)findViewById(R.id.register);

        int color = Color.parseColor("#ffffff");
        id.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        pw.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        //EditText 밑줄 색 바꾸는 코드

        retrofit = new Retrofit.Builder().baseUrl("http://nh.applepi.kr").addConverterFactory(GsonConverterFactory.create()).build();
        //레트로핏에 메인주소 연결

        final JSONService service = retrofit.create(JSONService.class);
        //JSONService 를 상속받은 service 변수 생성, 레트로핏과 연결

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_dialog = new ProgressDialog(LoginActivity.this);
                progress_dialog.setMessage("로그인 중입니다 ... ");
                progress_dialog.show();

                Call<User> call = service.login(id.getText().toString(), pw.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code() == 200){
                            User user = response.body();
                            if(user != null){
                                Toast.makeText(getApplicationContext(), "로그인 성공 ... ", Toast.LENGTH_SHORT).show();
                                Intent loginIntent = new Intent(LoginActivity.this, ChatListActivity.class);
                                loginIntent.putExtra("name", user.user_name);
                                loginIntent.putExtra("id", id.getText().toString());
                                startActivity(loginIntent);
                            }
                        }else {
                            progress_dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "로그인 실패 ... ", Toast.LENGTH_SHORT).show();
                            Intent loginIntent = new Intent(LoginActivity.this, ChatListActivity.class);
                            loginIntent.putExtra("name", "박태준");
                            loginIntent.putExtra("id", id.getText().toString());
                            startActivity(loginIntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progress_dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "요청 실패 ... ", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(LoginActivity.this, ChatListActivity.class);
                        loginIntent.putExtra("name", "박태준");
                        loginIntent.putExtra("id", id.getText().toString());
                        startActivity(loginIntent);
                    }
                });
            }
        });
        //로그인 서버 연동

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        //회원가입 버튼
    }
}
