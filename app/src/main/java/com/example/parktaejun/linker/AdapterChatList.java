package com.example.parktaejun.linker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        View view = null;
        if(position == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_top, null);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_firend, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(items.get(position).getName());
        }

        return view;
    }
}