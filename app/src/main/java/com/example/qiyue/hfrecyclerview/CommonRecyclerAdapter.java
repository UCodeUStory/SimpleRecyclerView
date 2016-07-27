package com.example.qiyue.hfrecyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerAdapter.ViewHolder>{


    public static final int HEADER_VIEW = 1;

    public static final int FOOTER_VIEW = 2;

    public static final int DATAS = 3;

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();

    public void setDatas(ArrayList<T> datas) {
        this.datas = datas;
    }

    private ArrayList<T> datas = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private int layout;
    public CommonRecyclerAdapter(Context context, @LayoutRes int layout){
        mLayoutInflater = LayoutInflater.from(context);
        this.layout = layout;
    }

    @Override
    public CommonRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW){
            return new ViewHolder(mHeaderViews.get(0));
        }else if(viewType == DATAS){
            return new ViewHolder(mLayoutInflater.inflate(layout, parent, false));
        }else{
            return new ViewHolder(mFooterViews.get(0));
        }
    }

    @Override
    public void onBindViewHolder(CommonRecyclerAdapter.ViewHolder holder, int position) {
        Log.i("qiyue","position="+position);
        if (DATAS == getItemViewType(position)) {
          //  holder.itemText.setText(datas.get(getDataPosition(position)));
            convert(holder,datas.get(getDataPosition(position)));
        }

    }

    public abstract void convert(ViewHolder holder, T t);

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

    public void removeHeaderView(View view){
        mHeaderViews.clear();
        this.notifyDataSetChanged();
    }

    public void removeFooterView(View view){
        mFooterViews.clear();
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
        private SparseArray<View> mViews = new SparseArray<View>();;
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public <T extends View> T getView(int viewId)
        {
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int id,String text){
            ((TextView)getView(id)).setText(text);
        }
    }
}
