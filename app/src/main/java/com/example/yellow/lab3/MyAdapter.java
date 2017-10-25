package com.example.yellow.lab3;
/*
*2017.10.20 yellow
* 参考自 http://www.jianshu.com/p/f2e0463e5aef
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

import static android.R.id.list;

/**
 * Created by Yellow on 2017-10-20.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<String> Name;
    private DataShare ds;
    /*private List<String> Price;
    private List<String> Type;
    private List<String> Details;
    private List<Boolean> isfavorite;
    private int favorflag;*/

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }


    public MyAdapter(DataShare context){
        ds=context;
        initData();
    }
    public void initData() {
        Name=new ArrayList<String>();
        Name=ds.getCurrentList();
        //ds.getPrice();
        /*Price=new ArrayList<String>();
        Type=new ArrayList<String>();
        Details=new ArrayList<String>();
        isfavorite=new ArrayList<Boolean>();
        String[] name={"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oasis","Waitrose 早餐麦片",
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
        }*/
    }
    //创建ChildView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(layout);//from(this)
    }

    //将数据绑定到每一个childView中
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.title.setText(Name.get(position));
        holder.iconletter.setText(Name.get(position).substring(0,1));
        //点击事件
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                //为ItemView设置监听器
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        //长按事件
        if(mOnItemLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    int position=holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    return true;//返回true 表示消耗了事件，若为false则还将执行短按click事件
                }
            });
        }
    }

    //得到child的数量
    @Override
    public int getItemCount() {
        return Name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView iconletter;
        private MyViewHolder(View view) {
            super(view);
            title= (TextView) view.findViewById(R.id.goods_name);
            iconletter=(TextView) view.findViewById(R.id.goods_icon_letter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener){
        this.mOnItemLongClickListener=mOnItemLongClickListener;
    }

}