package com.example.qiyue.hfrecyclerview;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String>datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<20;i++){
            index++;
            datas.add("data"+index);
        }
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // final HFRecyclerAdapter adapter = new HFRecyclerAdapter(this);
        final MyAdapter adapter = new MyAdapter(this,R.layout.content_item);
        adapter.setDatas(datas);
        recyclerView.setAdapter(adapter);
        LayoutInflater inflater = LayoutInflater.from(this);
        View header = inflater.inflate(R.layout.header_layout,null);
        View footer = inflater.inflate(R.layout.footer_layout,null);
        LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(params);
        footer.setLayoutParams(params);
        adapter.addHeaderView(header);
        adapter.addFooterView(footer);


        EndlessRecyclerOnScrollListener listener = new EndlessRecyclerOnScrollListener(){
            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                if (!isLoading) {
                    Toast.makeText(MainActivity.this, "loadMore", Toast.LENGTH_SHORT).show();
                    isLoading = true;
                    loadMore(1, 10, new LoadCallBack() {
                        @Override
                        public void success() {
                            adapter.notifyDataSetChanged();
                            isLoading = false;
                        }
                    });
                }
            }
        };

        recyclerView.addOnScrollListener(listener);



    }

    private int index = 0;

    private boolean isLoading = false;

    private void loadMore(int page, int pageSize, final LoadCallBack loadCallBack){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    index ++;
                    datas.add("data"+index);
                }
                loadCallBack.success();
            }
        },3000);
    }

    interface LoadCallBack{
        void success();
    }
}
