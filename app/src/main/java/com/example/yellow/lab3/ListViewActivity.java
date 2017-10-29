package com.example.yellow.lab3;

/**
 * Created by Yellow on 2017-10-21.
 */
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

    private NotificationManager myManager;
    private  int notificationid=1;

    private DynamicReceiver MBR;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_page);
        Bundle mBundle=getIntent().getExtras();
        String name=mBundle.getString("name");
        initView(name);//findviewbyid必须在setContentView之后否则出现空指针错误
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
    public void initView(String name){
        //Toast.makeText(ListViewActivity.this, name, Toast.LENGTH_LONG).show();
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
        /*ImageButton favorbtn=(ImageButton)findViewById(R.id.detail_page_favorite);
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
        detailtv.setText(ds.getType().get(pos)+" "+ds.getDetails().get(pos));*/
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
        /*if(!ds.isFavor(ds.getLastClick())) favbtn.setBackgroundResource(R.drawable.empty_star);
        else favbtn.setBackgroundResource(R.drawable.full_star);*///直接设置有bug
        initView(ds.getLastClick());//更新一下界面
    }
    public void addToCart(View taget){
        DataShare ds=((DataShare)getApplicationContext());
        ds.addIncart(ds.getLastClick());
        Toast.makeText(ListViewActivity.this, "商品已添加到购物车", Toast.LENGTH_SHORT).show();

        /*
        //以下使用系统默认通知，在各厂商UI下效果不一致
        Notification.Builder builder=new Notification.Builder(ListViewActivity.this);
        builder.setSmallIcon(ds.getIcon(ds.getName().indexOf(ds.getLastClick())));
        //builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                ds.getIcon(ds.getName().indexOf(ds.getLastClick()))));
        builder.setTicker("抢先下单");
        builder.setContentTitle("马上下单");
        builder.setContentText(ds.getLastClick()+"已添加到购物车");
        Intent intent=new Intent(ListViewActivity.this,CartActivity.class);
        PendingIntent ma=PendingIntent.getActivity(ListViewActivity.this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(ma);
        //builder.setDefaults(Notification.DEFAULT_SOUND);//设置声音 
        //builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯 
        //builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        builder.setDefaults(Notification.DEFAULT_ALL);//设置全部
        builder.setAutoCancel(true);
        Notification notification=builder.build();//在此之后通过notification.(其他属性设置）同样可以设置通知效果
        myManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myManager.notify(notificationid,notification);
*/

        //以下使用自定义通知栏,直接通知
        /*RemoteViews notiView=new RemoteViews(getPackageName(),R.layout.notification_view);
        notiView.setImageViewResource(R.id.notification_icon,R.drawable.peanut2);
        notiView.setTextViewText(R.id.notification_title,"马上下单");
        notiView.setTextViewText(R.id.notification_text,ds.getLastClick()+"已添加到购物车");
        notiView.setTextViewText(R.id.notification_time,getTime());
        notiView.setImageViewResource(R.id.notification_img,ds.getIcon(ds.getName().indexOf(ds.getLastClick())));

        Intent intent=new Intent(ListViewActivity.this,CartActivity.class);
        PendingIntent ma=PendingIntent.getActivity(ListViewActivity.this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder mbuilder=new Notification.Builder(ListViewActivity.this)
                .setSmallIcon(ds.getIcon(ds.getName().indexOf(ds.getLastClick())))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), ds.getIcon(ds.getName().indexOf(ds.getLastClick()))))
                .setTicker("抢先下单")
                .setContentTitle("马上下下单")
                .setContentText(ds.getLastClick()+"已添加到购物车")
                .setContentIntent(ma)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContent(notiView)
                .setAutoCancel(true);
        Notification notification=mbuilder.build();
        myManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myManager.notify(notificationid,notification);*/


        //以下通过注册动态广播实现
        MBR=new DynamicReceiver();//MBR为私有静态接收器，实现了一个按键来注销
        IntentFilter dynamic_filter=new IntentFilter();
        dynamic_filter.addAction("DYNAMIC_ACTION");
        registerReceiver(MBR,dynamic_filter);
        Intent broadcastIntent=new Intent();
        broadcastIntent.putExtra("package_name",ds.getLastClick());
        broadcastIntent.setAction("DYNAMIC_ACTION");
        sendBroadcast(broadcastIntent);

    }

    public String getTime(){
        Calendar cal=Calendar.getInstance();
        String time=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        return time;
    }

    public void myUnregister(View view){
        if(MBR!=null){
            unregisterReceiver(MBR);
        }
    }

}