package com.example.parktaejun.linker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaejun.linker.Adapter.AdapterChatList;
import com.example.parktaejun.linker.Data.ChatList;
import com.example.parktaejun.linker.Server.JSONService;
import com.example.parktaejun.linker.Server.User;
import com.mikhaellopez.circularimageview.CircularImageView;

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

        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.image);
        circularImageView.setBorderColor(getResources().getColor(R.color.white));
        circularImageView.setBorderWidth(5);

        items.add(new ChatList("박태준"));
        listview.setAdapter(adapterChatList);
        loadList();

        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        String chat_name = items.get(i).getName();
                        Intent chatIntent = new Intent(ChatListActivity.this, ChatInsideActivity.class);
                        chatIntent.putExtra("chatName", chat_name);
                        startActivity(chatIntent);
                    }
                }
        );
    }

    public void initList(String name){
        items.add(new ChatList(name));
        adapterChatList.notifyDataSetChanged();
    }

    public void loadList(){
        final JSONService loadPost = retrofit.create(JSONService.class);
        Call<List<User>> call = loadPost.loadlist();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.code() == 200){
                    List<User> users = response.body();
                    for(User user : users){
                        initList(user.user_name);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "요청 실패 ...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
