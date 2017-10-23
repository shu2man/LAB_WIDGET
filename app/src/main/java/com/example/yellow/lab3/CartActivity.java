package com.example.yellow.lab3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnLongClickListener;

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
    private SimpleAdapter adapter=null;
    private List<String> Name=null;
    private List<String> Price=null;
    private List<String> Iconletter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        initData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)view.findViewById(R.id.cartlistname);
                //Toast.makeText(getApplicationContext(),tv.getText(),Toast.LENGTH_SHORT).show();
                if(!tv.getText().toString().equals("        购物车空空如也~")) {
                    DataShare ds=((DataShare)getApplicationContext());
                    ds.setLastClick(tv.getText().toString());
                    ds.setLastPage("cart");
                    Intent newpage = new Intent(CartActivity.this, ListViewActivity.class);
                    CartActivity.this.startActivity(newpage);
                    CartActivity.this.finish();
                }
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView tv=(TextView) view.findViewById(R.id.cartlistname);
                //Toast.makeText(getApplicationContext(),tv.getText(),Toast.LENGTH_SHORT).show();
                if(tv.getText().toString().equals("        购物车空空如也~")) return true;
                AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("移除商品");
                builder.setMessage("从购物车中移除"+tv.getText()+"?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Doing Nothing
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataShare ds=((DataShare)getApplicationContext());
                        ds.removeIncart(tv.getText().toString());
                        initData();
                        Toast.makeText(getApplicationContext(),"商品"+tv.getText()+"移除成功",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public void initData(){
        List<Map<String,Object>>Items=new ArrayList<Map<String,Object>>();
        DataShare ds=((DataShare)getApplicationContext());
        int len=0;
        if(ds.getIncart()!=null) len=ds.getIncart().size();
        List<String> Name=new ArrayList<String>();
        List<String> Price=new ArrayList<String>();
        List<String> Iconletter=new ArrayList<String>();
        if(len==0){
            Map<String,Object> item=new HashMap<String,Object>();
            item.put("iconletter","#");
            item.put("name","        购物车空空如也~");//8个空格在前
            item.put("price","    ");
            Items.add(item);
        }
        else{
            for(int i=0;i<len;i++){
                Iconletter.add(ds.getIncart().get(i).substring(0,1));
                Name.add(ds.getIncart().get(i));
                Price.add(ds.getPrice().get(ds.getName().indexOf(ds.getIncart().get(i))));
            }
            for(int i=0;i<len;i++){
                Map<String,Object> item=new HashMap<String,Object>();
                item.put("iconletter",Iconletter.get(i));
                item.put("name",Name.get(i));
                item.put("price",Price.get(i));
                Items.add(item);
            }
        }
        //以下为本地初始化数据，activity间无通信
        /*List<String> Name=new ArrayList<String>();
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
        for(int i=0;i<10;i++){
            Map<String,Object> item=new HashMap<String,Object>();
            item.put("iconletter",Iconletter.get(i));
            item.put("name",Name.get(i));
            item.put("price",Price.get(i));
            Items.add(item);
        }*/
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
        this.finish();
    }



}
