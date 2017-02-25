package com.example.parktaejun.linker;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by parktaejun on 2017. 2. 23..
 */

public class Font {
    public static void setGlobalFont(Context context, View view){
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                int len = vg.getChildCount();
                for (int i = 0; i < len; i++) {
                    View v = vg.getChildAt(i);
                    if (v instanceof TextView) {
                        ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic.otf"));
                        //Project -> app -> src -> main -> assets 폴더 생성 후에 폰트 파일 넣어줌
                    }
                    setGlobalFont(context, v);
                }
            }
        } else {
            Log.d("err", "err");
        }

    }
}
