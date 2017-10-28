package com.example.yellow.lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;

import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Yellow on 2017-10-28.
 */

public class DynamicReceiver extends BroadcastReceiver {
    private NotificationManager myManager;
    private int notificationid=2;


    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("DYNAMIC_ACTION")){
            String name=intent.getStringExtra("package_name");
            setNotification(name,context);
        }
    }
    public void setNotification(String name,Context context){
        DataShare ds=((DataShare)context.getApplicationContext());
        RemoteViews notiView=new RemoteViews(context.getPackageName(),R.layout.notification_view);
        notiView.setImageViewResource(R.id.notification_icon,R.drawable.peanut2);
        notiView.setTextViewText(R.id.notification_title,"马上下单");
        notiView.setTextViewText(R.id.notification_text,ds.getLastClick()+"已添加到购物车");
        notiView.setTextViewText(R.id.notification_time,getTime());
        notiView.setImageViewResource(R.id.notification_img,ds.getIcon(ds.getName().indexOf(ds.getLastClick())));

        Intent intent=new Intent(context,CartActivity.class);
        PendingIntent ma=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder mbuilder=new Notification.Builder(context)
                .setSmallIcon(ds.getIcon(ds.getName().indexOf(ds.getLastClick())))
                .setTicker("抢先下单")
                .setContentTitle("马上下下单")
                .setContentText(ds.getLastClick()+"已添加到购物车")
                .setContentIntent(ma)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContent(notiView)
                .setAutoCancel(true);
        Notification notification=mbuilder.build();
        myManager=(NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        myManager.notify(notificationid,notification);

    }

    public String getTime(){
        Calendar cal=Calendar.getInstance();
        String time=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        return time;
    }
}
