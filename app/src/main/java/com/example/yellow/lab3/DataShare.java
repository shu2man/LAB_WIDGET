package com.example.yellow.lab3;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yellow on 2017-10-21.
 */

public class DataShare extends Application{
    private List<String> Name;
    private List<String> Price;
    private List<String> Type;
    private List<String> Details;
    private List<String> Favor;
    private List<String> Incart;
    private List<String> CurrentList;
    private int[] Icon={R.drawable.enchatedforest,R.drawable.arla,R.drawable.devondale,R.drawable.kindle,R.drawable.waitrose,
            R.drawable.mcvitie,R.drawable.ferrero,R.drawable.maltesers,R.drawable.lindt,R.drawable.borggreve};
    private String lastPage;
    private String lastClick;
    private int mainNum=0;

   public DataShare(){
        Name=new ArrayList<String>();
        Price=new ArrayList<String>();
        Type=new ArrayList<String>();
        Details=new ArrayList<String>();
        Favor=new ArrayList<String>();
        Incart=new ArrayList<String>();
        String[] name={"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oasis","waitrose 早餐麦片",
                "Mcvitie's 饼干","Ferrero Rocher","Maltesers","Lindt","Borggreve"};
        String[] price={"¥5.00","¥59.00","¥79.00","¥2399.00","¥179.00","¥14.90","¥132.59","¥141.43","¥139.43","¥28.90"};
        String[] type={"作者","产地","版本","版本","重量","重量","重量","重量","重量","重量"};
        String[] details={"Johanna Basford","德国","澳大利亚","8GB","2Kg","英国","300","118g","249g","640g"};
        for(int i=0;i<10;i++){
            Name.add(name[i]);
            Price.add(price[i]);
            Type.add(type[i]);
            Details.add(details[i]);
        }
        CurrentList=Name;
   }

    public List<String> getName(){
        return Name;
    }
    public List<String> getType(){
        return Type;
    }
    public List<String> getPrice(){
        return Price;
    }
    public List<String> getDetails(){
        return Details;
    }
    public List<String> getCurrentList() {
        return CurrentList;
    }
    public List<String> getIncart() {
        return Incart;
    }
    public String getLastPage(){return lastPage;}
    public String getLastClick(){return lastClick;}
    public int getIcon(int position){return Icon[position];}
    public Boolean isFavor(String name){ return Favor.contains(name);}

    public void removeCurrentList(String name){if(CurrentList.contains(name)) CurrentList.remove(name);}
    public void setFavor(String name) {
        if(Favor.contains(name)) Favor.remove(name);
        else Favor.add(name);
    }

    public void addIncart(String name) {
        if(!Incart.contains(name)) Incart.add(name);
    }
    public void removeIncart(String name){if(Incart.contains(name)) Incart.remove(name);}
    public void setLastPage(String page){lastPage=page;}
    public void setLastClick(String name){lastClick=name;}
    public int getMainNum(){
        mainNum+=1;
        return mainNum;
    }
}
