package com.example.qiyue.hfrecyclerview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class HFRecyclerAdapter extends RecyclerView.Adapter<HFRecyclerAdapter.ViewHolder> {

    public static final int HEADER_VIEW = 1;

    public static final int FOOTER_VIEW = 2;

    public static final int DATAS = 3;

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
    }

    private ArrayList<String> datas = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public HFRecyclerAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HFRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW){
            return new ViewHolder(mHeaderViews.get(0));
        }else if(viewType == DATAS){
            return new ViewHolder(mLayoutInflater.inflate(R.layout.content_item, parent, false));
        }else{
            return new ViewHolder(mFooterViews.get(0));
        }
    }

    @Override
    public void onBindViewHolder(HFRecyclerAdapter.ViewHolder holder, int position) {
        Log.i("qiyue","position="+position);
        if (DATAS == getItemViewType(position)) {
            holder.itemText.setText(datas.get(getDataPosition(position)));
        }

    }

    @Override
    public int getItemCount() {
        Log.i("qiyue","datas.size()="+datas.size());
        Log.i("qiyue","mHeaderViews.size()"+mHeaderViews.size());
        return mHeaderViews.size()+mFooterViews.size()+datas.size();
    }

    public int getDataPosition(int position){
        position = position - mHeaderViews.size();
        return position;
    }

    public void addHeaderView(View view){
        mHeaderViews.add(view);
        this.notifyDataSetChanged();
    }

    public void addFooterView(View view){
        mFooterViews.add(view);
        this.notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {

        if (position < mHeaderViews.size()) {
            return HEADER_VIEW;
        } else if (mHeaderViews.size() <= position && position < mHeaderViews.size() + datas.size()) {
            return DATAS;
        } else {
            return FOOTER_VIEW;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemText;
        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView.getId()==R.id.content_layout){
                itemText = (TextView)itemView.findViewById(R.id.tv_text);
            }

        }
    }

}
