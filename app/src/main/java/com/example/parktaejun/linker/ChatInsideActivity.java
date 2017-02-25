package com.example.parktaejun.linker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parktaejun.linker.Adapter.AdapterChatInside;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChatInsideActivity extends AppCompatActivity {

    private ListView listview;
    private List<JSONObject> items = new ArrayList<>();
    private AdapterChatInside adapterChatInside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_inside);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View toolbar_view = LayoutInflater.from(this).inflate(R.layout.toolbar, null);
        TextView title = (TextView) toolbar_view.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar_view.findViewById(R.id.back);
        title.setText(intent.getStringExtra("chatName"));
        getSupportActionBar().setCustomView(toolbar_view);

        listview = (ListView)findViewById(R.id.chatting_list);
        listview.setFooterDividersEnabled(false);
        listview.setHeaderDividersEnabled(false);
        listview.setDividerHeight(0);
        adapterChatInside = new AdapterChatInside(this, items);
        listview.setAdapter(adapterChatInside);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSocket.connect();
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText)findViewById(R.id.chatting_text);
                String msg = edit.getText().toString();
                mSocket.emit("chat message", msg);
                JSONObject json = new JSONObject();
                try{
                    json.put("who", "me");
                    json.put("msg", msg);
                    addChat(json);
                } catch(JSONException e){
                    e.printStackTrace();
                }
                edit.setText("");
            }
        });
        mSocket.on("send message", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                JSONObject json = new JSONObject();
                try{
                    json.put("who", "other");
                    json.put("msg", data.getString("msg"));
                    addChat(json);
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void addChat(JSONObject json){
        items.add(json);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterChatInside.notifyDataSetChanged();
            }
        });
    }

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://nh.applepi.kr");
        } catch (URISyntaxException e){}
    }
}