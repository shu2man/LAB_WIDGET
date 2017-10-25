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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyAdapter mAdapter=new MyAdapter((DataShare)getApplicationContext());//this
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv=(TextView)view.findViewById(R.id.goods_name);
                DataShare ds=((DataShare)getApplicationContext());
                ds.setLastClick(tv.getText().toString());
                ds.setLastPage("main");
                goToListViewActivity();//跳转到商品详情界面
            }
        });
        mAdapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                TextView tv=(TextView)view.findViewById(R.id.goods_name);
                DataShare ds=((DataShare)getApplicationContext());
                ds.removeCurrentList(tv.getText().toString());
                mAdapter.initData();
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"成功移除第"+position+"个商品 "+tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //动画
        ScaleInAnimationAdapter animationAdapter=new ScaleInAnimationAdapter(mAdapter);
        animationAdapter.setDuration(1000);
        mRecyclerView.setAdapter(animationAdapter);
        mRecyclerView.setItemAnimator(new OvershootInLeftAnimator());
        //
        mRecyclerView.setAdapter(mAdapter);
        //分割线
        mRecyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));

    }

   public void goToCart(View target){
        Intent page=new Intent(MainActivity.this,CartActivity.class);
        startActivity(page);
        DataShare ds=((DataShare)getApplicationContext());
        if(ds.getMainNum()!=1) this.finish();
   }
    public void goToListViewActivity(){
        Intent page=new Intent(MainActivity.this,ListViewActivity.class);
        startActivity(page);
        DataShare ds=((DataShare)getApplicationContext());
        if(ds.getMainNum()!=1) this.finish();//待优化
    }

}