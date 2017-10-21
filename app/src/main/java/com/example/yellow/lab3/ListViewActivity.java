package com.example.yellow.lab3;

/**
 * Created by Yellow on 2017-10-21.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {
    private ListView listView=null;
   private int favorflag=0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_page);
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
    public void backToRecyclerView(View target){
        Intent newpage=new Intent(ListViewActivity.this,MainActivity.class);
        ListViewActivity.this.startActivity(newpage);
    }
    public void setFavorite(View view){
        favorflag++;
        if(favorflag%2 != 0) view.setBackgroundResource(R.drawable.full_star);
        else view.setBackgroundResource(R.drawable.empty_star);
    }
}
