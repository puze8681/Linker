package com.example.parktaejun.linker.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parktaejun.linker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 25..
 */

public class AdapterChatInside extends BaseAdapter {

    private Context context;
    private List<JSONObject> jsonItems;

    public AdapterChatInside(Context context, List<JSONObject> jsonItems){
        this.context = context;
        this.jsonItems = jsonItems;
    }

    @Override
    public int getCount() {
        return jsonItems.size();
    }

    @Override
    public Object getItem(int position) {
        return jsonItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JSONObject jsonItem = jsonItems.get(position);
        Drawable background = null;
        String msg = "";

        View view = LayoutInflater.from(context).inflate(R.layout.chat, null);
        LinearLayout container = (LinearLayout) view.findViewById(R.id.container);
        TextView chat_card = (TextView) view.findViewById(R.id.chat_box);

        try{
            if(jsonItem.getString("who").equals("me")){
                container.setGravity(Gravity.RIGHT);
                background = container.getResources().getDrawable(R.drawable.chat_box_me, context.getTheme());
            } else {
                background = container.getResources().getDrawable(R.drawable.chat_box_other, context.getTheme());
            }
            msg = jsonItem.getString("msg");
        } catch (JSONException e){
            e.printStackTrace();
        }
        chat_card.setBackground(background);
        chat_card.setText(msg);

        return view;
    }


}