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
import java.util.Locale;

/**
 * Created by Yellow on 2017-10-21.
 */

public class CartActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<String> ndata=null;
    private List<String> pdata=null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        initData();
    }

    public void backToRecyclerView(View target){
        Intent newpage=new Intent(CartActivity.this,MainActivity.class);
        CartActivity.this.startActivity(newpage);
    }
    public void initData(){

    }

}
