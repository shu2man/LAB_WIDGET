package com.example.yellow.lab3;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Yellow on 2017-10-21.
 */

public class CartActivity extends AppCompatActivity{
    private ListView mListView=null;
    /*private MyAdapter mAdapter;
    private List<String> Name=null;
    private List<String> Price=null;
    private List<String> Iconletter=null;*/


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        initData();
        //mListView=(ListView) findViewById(R.id.cart_listview);

    }

    public void initData(){
        List<String> Name=new ArrayList<String>();
        List<String> Price=new ArrayList<String>();
        List<String> Iconletter=new ArrayList<String>();
        String[] name={"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oasis","waitrose 早餐麦片",
                "Mcvitie's 饼干","Ferrero Rocher","Maltesers","Lindt","Borggreve"};
        String[] price={"¥5.00","¥59.00","¥79.00","¥2399.00","¥179.00","¥14.90","¥132.59","¥141.43","¥139.43","¥28.90"};
        for(int i=0;i<10;i++){
            Iconletter.add(name[i].substring(0,1));
            Name.add(name[i]);
            Price.add(price[i]);
        }
        List<Map<String,Object>>Items=new ArrayList<Map<String,Object>>();
        for(int i=0;i<10;i++){
            Map<String,Object> item=new HashMap<String,Object>();
            item.put("iconletter",Iconletter.get(i));
            item.put("name",Name.get(i));
            item.put("price",Price.get(i));
            Items.add(item);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,
                Items,
                R.layout.cart_item,
                new String[]{"iconletter","name","price"},
                new int[]{R.id.cartlistcircle,R.id.cartlistname,R.id.cartlistprice});
        mListView=(ListView) findViewById(R.id.cart_listview);
        mListView.setAdapter(adapter);
    }

    public void backToRecyclerView(View target){
        Intent newpage=new Intent(CartActivity.this,MainActivity.class);
        CartActivity.this.startActivity(newpage);
    }

}
