package com.example.yellow.lab3;

/**
 * Created by Yellow on 2017-10-19.
 * MyRecyclerView和MyAdapter借鉴自  http://blog.csdn.net/lmj623565791/article/details/45059587
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
/*
public class RecyclerViewActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//activity_single_recyclerview
        //initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));
    }*/
/*
    protected void initData() {
        Name=new ArrayList<String>();
        Price=new ArrayList<String>();
        Type=new ArrayList<String>();
        Details=new ArrayList<String>();
        isfavorite=new ArrayList<Boolean>();
        String[] name={"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oasis","waitrose 早餐麦片",
                        "Mcvitie's 饼干","Ferrero Rocher","Maltesers","Lindt","Borggreve"};
        String[] price={"¥5.00","¥59.00","¥79.00","¥2399.00","¥179.00","¥14.90","¥132.59","¥141.43","¥139.43","¥28.90"};
        String[] type={"作者","产地","版本","版本","重量","重量","重量","重量","重量","重量"};
        String[] details={"Johanna Basford","德国","澳大利亚","8GB","2Kg","英国","300","118g","249g","640g"};
        favorflag=0;
        for(int i=0;i<10;i++){
            Name.add(name[i]);
            Price.add(price[i]);
            Type.add(type[i]);
            Details.add(details[i]);
            isfavorite.add(false);
        }
    }*/
/*
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        //创建ChildView
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecyclerViewActivity.this).inflate(R.layout.list_item, parent,
                    false));
            return holder;
        }

        //将数据绑定到每一个childView中
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.title.setText(Name.get(position));
            holder.iconletter.setText(Name.get(position).substring(0,1));

        }

        //得到child的数量
        @Override
        public int getItemCount() {
            return Name.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView iconletter;
            public MyViewHolder(View view) {
                super(view);
                title= (TextView) view.findViewById(R.id.goods_name);
                iconletter=(TextView) view.findViewById(R.id.goods_icon_letter);
            }
        }

        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
            mRecyclerView.mOn
        }
    }
*/
 /*   public class MyListView extends Activity{
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
    }*/

   /* public void setFavorite(View view){
        favorflag++;
        view=(ImageButton) findViewById(R.id.detail_page_favorite);
        if(favorflag%2 != 0) view.setBackgroundResource(R.drawable.full_star);
        else view.setBackgroundResource(R.drawable.empty_star);
    }*/


//}

