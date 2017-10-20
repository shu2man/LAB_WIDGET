package com.example.yellow.lab3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<String> mData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter=new MyAdapter(this);
        /*mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "CLICK", Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "LONG_CLICK", Toast.LENGTH_SHORT).show();
            }
        });*/

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
    }
/*    public class MyListView extends Activity {
        private ListView listView;
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            listView=(ListView) findViewById(R.id.listview_in_detail_page);
            listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));
            setContentView(listView);
        }
        private List<String> getData(){
            List<String> data=new ArrayList<String>();
            data.add("一键下单");
            data.add("分享商品");
            data.add("不感兴趣");
            data.add("查看更多商品促销信息");
            return data;
        }
    }

    public void setFavorite(View view){
        //favorflag++;
        view=(ImageButton) findViewById(R.id.detail_page_favorite);
        *//*if(favorflag%2 != 0) *//*view.setBackgroundResource(R.drawable.full_star);
        *//*else *//*view.setBackgroundResource(R.drawable.empty_star);
    }*/
}