package com.example.yellow.lab3;

/**
 * Created by Yellow on 2017-10-21.
 */
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListViewActivity extends Activity {
    private ListView listView=null;
    private String name;
    private NotificationManager myManager;
    private  int notificationid=1;

    private DynamicReceiver MBR;
    private MyWidget DynamicWidget;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_page);
        Bundle mBundle=getIntent().getExtras();
        name=mBundle.getString("name");
        initView();//findviewbyid必须在setContentView之后否则出现空指针错误

        MBR=new DynamicReceiver();//MBR为私有静态接收器，实现了一个按键来注销
        IntentFilter dynamic_filter=new IntentFilter();
        dynamic_filter.addAction("DYNAMIC_ACTION");
        registerReceiver(MBR,dynamic_filter);

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
    public void initView(){//String name
        Toast.makeText(ListViewActivity.this, name, Toast.LENGTH_LONG).show();
        ImageButton favorbtn=(ImageButton)findViewById(R.id.detail_page_favorite);
        ImageView imgiv=(ImageView) findViewById(R.id.goods_image);
        TextView nametv=(TextView) findViewById(R.id.detail_page_name);
        TextView pricetv=(TextView) findViewById(R.id.price_textview);
        TextView detailtv=(TextView) findViewById(R.id.type_textview);
        DataShare ds=((DataShare)getApplicationContext());
        int pos=ds.getName().indexOf(name);
        if (ds.isFavor(name)) favorbtn.setImageResource(R.drawable.full_star);
        else favorbtn.setImageResource(R.drawable.empty_star);
        imgiv.setImageResource(ds.getIcon(pos));
        nametv.setText(name);
        pricetv.setText(ds.getPrice().get(pos));
        detailtv.setText(ds.getType().get(pos)+" "+ds.getDetails().get(pos));
    }
    public void backToLastPage(View target){
        DataShare ds=((DataShare)getApplicationContext());
        if(ds.getLastPage().equals("main")){
            Intent newpage=new Intent(ListViewActivity.this,MainActivity.class);
            ListViewActivity.this.startActivity(newpage);
            this.finish();
        }
        else{
            Intent newpage=new Intent(ListViewActivity.this,CartActivity.class);
            ListViewActivity.this.startActivity(newpage);
            this.finish();
        }
    }
    public void setFavorite(View view){
        DataShare ds=((DataShare)getApplicationContext());
        ds.setFavor(ds.getLastClick());
        ImageButton favbtn=(ImageButton)findViewById(R.id.detail_page_favorite);
        initView();//更新一下界面,ds.getLastClick()
    }
    public void addToCart(View taget){
        DataShare ds=((DataShare)getApplicationContext());
        ds.addIncart(ds.getLastClick());
        Toast.makeText(ListViewActivity.this, "商品已添加到购物车", Toast.LENGTH_SHORT).show();

        //以下通过注册动态广播实现
        Intent broadcastIntent1=new Intent();
        broadcastIntent1.putExtra("package_name",ds.getLastClick());
        broadcastIntent1.setAction("DYNAMIC_ACTION");
        sendBroadcast(broadcastIntent1);

    }

    public String getTime(){
        Calendar cal=Calendar.getInstance();
        String time=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        return time;
    }

    public void myUnregister(View view){
        if(MBR!=null){
            unregisterReceiver(MBR);
            Toast.makeText(ListViewActivity.this, "接收器1已注销", Toast.LENGTH_SHORT).show();
        }
        if(DynamicWidget!=null){
            unregisterReceiver(DynamicWidget);
            Toast.makeText(ListViewActivity.this, "接收器2已注销", Toast.LENGTH_SHORT).show();
        }
    }

}