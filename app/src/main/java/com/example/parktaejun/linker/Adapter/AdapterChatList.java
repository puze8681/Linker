package com.example.parktaejun.linker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parktaejun.linker.Data.ChatList;
import com.example.parktaejun.linker.R;

import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 25..
 */

public class AdapterChatList extends BaseAdapter {

    private Context context;
    private List<ChatList> items;

    public AdapterChatList(Context context, List<ChatList> items){
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = LayoutInflater.from(context).inflate(R.layout.chat, null);
        LinearLayout container = (LinearLayout) view.findViewById(R.id.container);

        if(position == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_top, null);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_firend, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(items.get(position).getName());
        }

        return view;
    }
}