package com.example.parktaejun.linker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatListActivity extends AppCompatActivity {


    private ListView listview;
    private AdapterChatList adapterChatList;
    public static List<ChatList> items = new ArrayList<>();
    TextView name;
    TextView id;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        name = (TextView)findViewById(R.id.name);
        id = (TextView)findViewById(R.id.id);
        Intent intent = getIntent();
        name.setText(intent.getExtras().getString("name"));
        id.setText("@" + intent.getExtras().getString("id"));
        items.add(new ChatList("박태준"));

    }

    public void initList(String name){
        items.add(new ChatList(name));
        adapterChatList.notifyDataSetChanged();
    }

    public void loadPost(){
        final JSONService loadPost = retrofit.create(JSONService.class);
        Call<List<User>> call = loadPost.loadlist();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        loadPost();
        mBaseLayout.setRefreshing(false);

    }
}
