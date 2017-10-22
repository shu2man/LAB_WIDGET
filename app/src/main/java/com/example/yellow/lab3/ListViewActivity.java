package com.example.yellow.lab3;

/**
 * Created by Yellow on 2017-10-21.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {
    private ListView listView=null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_page);
        initView(this);//findviewbyid必须在setContentView之后否则出现空指针错误
        listView=(ListView) findViewById(R.id.listview_in_detail_page);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));
    }
    private List<String> getData(){
        List<String> data=new ArrayList<String>();
        data.add("一键下单");
        data.add("分享商品");
        data.add("不感兴趣");
        data.add("查看更多商品促销信息");
        return data;
    }
    public void initView(Context context){
        ImageButton favorbtn=(ImageButton)findViewById(R.id.detail_page_favorite);
        ImageView imgiv=(ImageView) findViewById(R.id.goods_image);
        TextView nametv=(TextView) findViewById(R.id.detail_page_name);
        TextView pricetv=(TextView) findViewById(R.id.price_textview);
        TextView detailtv=(TextView) findViewById(R.id.type_textview);
        DataShare ds=((DataShare)getApplicationContext());
        int pos=ds.getName().indexOf(ds.getLastClick());
        if (ds.isFavor(ds.getLastClick())) favorbtn.setImageResource(R.drawable.full_star);
        else favorbtn.setImageResource(R.drawable.empty_star);
        imgiv.setImageResource(ds.getIcon(pos));
        nametv.setText(ds.getLastClick());
        pricetv.setText(ds.getPrice().get(pos));
        detailtv.setText(ds.getType().get(pos)+" "+ds.getDetails().get(pos));
    }
    public void backToLastPage(View target){
        DataShare ds=((DataShare)getApplicationContext());
        if(ds.getLastPage().equals("main")){
            Intent newpage=new Intent(ListViewActivity.this,MainActivity.class);
            ListViewActivity.this.startActivity(newpage);
        }
        else{
            Intent newpage=new Intent(ListViewActivity.this,CartActivity.class);
            ListViewActivity.this.startActivity(newpage);
        }
    }
    public void setFavorite(View view){
        DataShare ds=((DataShare)getApplicationContext());
        ds.setFavor(ds.getLastClick());
        ImageButton favbtn=(ImageButton)findViewById(R.id.detail_page_favorite);
        /*if(!ds.isFavor(ds.getLastClick())) favbtn.setBackgroundResource(R.drawable.empty_star);
        else favbtn.setBackgroundResource(R.drawable.full_star);*/
        initView(this);
        //Toast.makeText(getApplicationContext(),"操作成功",Toast.LENGTH_SHORT).show();
    }
    public void addToCart(View taget){
        DataShare ds=((DataShare)getApplicationContext());
        ds.addIncart(ds.getLastClick());
        Toast.makeText(ListViewActivity.this, "商品已添加到购物车", Toast.LENGTH_SHORT).show();
    }

}