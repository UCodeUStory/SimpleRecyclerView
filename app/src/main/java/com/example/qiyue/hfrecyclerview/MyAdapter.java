package com.example.qiyue.hfrecyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class MyAdapter extends CommonRecyclerAdapter<String> {

    public MyAdapter(Context context, @LayoutRes int layout) {
        super(context, layout);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.tv_text,s);
    }
}
