package com.example.yellow.lab3;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
//MyWidget 继承自 AppWidgetProvider，而 AppWidgetProvider 又是继承自 BroadCastReceiver
public class MyWidget extends AppWidgetProvider {
    private String name;
    private AppWidgetManager widgetManager;
    private int widgetId;
    private String action="EpmtyString";

    @Override
    public void onReceive(Context context, Intent intent){
        super.onReceive(context,intent);
        action=intent.getAction();
        if(intent.getStringExtra("package_name")==null) return;
        name=intent.getStringExtra("package_name");
        //Toast.makeText(context,name+" received",Toast.LENGTH_SHORT).show();
        widgetManager=AppWidgetManager.getInstance(context);
        widgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        updateAppWidget2(context,widgetManager,widgetId);
    }

    //static
    public void updateAppWidget2(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        // Construct the RemoteViews object
        if (action.equals("OnLaunchApp")){
            Intent intent=new Intent(context,ListViewActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("name",name);
            intent.putExtras(bundle);
            //intent.setComponent(new ComponentName(context,MyWidget.class));
            DataShare ds=((DataShare)context.getApplicationContext());
            int pos = ds.getName().indexOf(name);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            PendingIntent pintent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setTextViewText(R.id.appwidget_text, name+"仅售"+ds.getPrice().get(pos)+" !");
            views.setImageViewResource(R.id.widget_icon,ds.getIcon(pos));
            views.setOnClickPendingIntent(R.id.widget_icon,pintent);
            views.setOnClickPendingIntent(R.id.appwidget_text,pintent);
            // Instruct the widget manager to update the widget,
            appWidgetManager.updateAppWidget(new ComponentName(context,MyWidget.class), views);
            //appWidgetManager.updateAppWidget(widgetId, views);
        }
        else if(action.equals("OnAddToCart")){
            Intent intent=new Intent(context,CartActivity.class);
            DataShare ds=((DataShare)context.getApplicationContext());
            int pos = ds.getName().indexOf(name);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            PendingIntent pintent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setTextViewText(R.id.appwidget_text, name+"已添加到购物车");
            views.setImageViewResource(R.id.widget_icon,ds.getIcon(pos));
            views.setOnClickPendingIntent(R.id.widget_icon,pintent);
            views.setOnClickPendingIntent(R.id.appwidget_text,pintent);
            appWidgetManager.updateAppWidget(new ComponentName(context,MyWidget.class), views);
        }
    }

   @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       super.onUpdate(context, appWidgetManager, appWidgetIds);
       if(!action.equals("DYNAMIC_ACTION2") && !action.equals("OnLaunchApp")){
           Intent intent=new Intent(context,MainActivity.class);
           DataShare ds=((DataShare)context.getApplicationContext());
           int pos = ds.getName().indexOf(name);
           RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
           PendingIntent pintent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
           views.setTextViewText(R.id.appwidget_text, "当前没有任何消息~");
           views.setImageViewResource(R.id.widget_icon,R.drawable.peanut2);
           views.setOnClickPendingIntent(R.id.widget_icon,pintent);
           views.setOnClickPendingIntent(R.id.appwidget_text,pintent);
           appWidgetManager.updateAppWidget(new ComponentName(context,MyWidget.class), views);
       }
       else {
           // There may be multiple widgets active, so update all of them
           for (int appWidgetId : appWidgetIds) {
               updateAppWidget2(context, appWidgetManager, appWidgetId);
           }
       }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

