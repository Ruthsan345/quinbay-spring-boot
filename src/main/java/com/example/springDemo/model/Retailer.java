package com.example.springDemo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Retailer {

    int retail_id;
    String retail_name;
    //    ArrayList<Product> retail_products =new ArrayList<Product>();
    //  ArrayList<Wholesaler> retail_products =new ArrayList<Wholesaler>();
    HashMap<Integer,ArrayList<Product> > retail_products=new HashMap<Integer,ArrayList<Product> >();

   public Retailer(){}
   public Retailer( int id, String name,HashMap<Integer,ArrayList<Product> > retailersList){
        this.retail_id = id;
        this.retail_name = name;
//        this.retail_products = new ArrayList<Product>();
        this.retail_products = new HashMap<Integer,ArrayList<Product> >();

    }

    public int getRetail_id() {
        return retail_id;
    }

    public void setRetail_id(int retail_id) {
        this.retail_id = retail_id;
    }

    public String getRetail_name() {
        return retail_name;
    }

    public void setRetail_name(String retail_name) {
        this.retail_name = retail_name;
    }

    public HashMap<Integer, ArrayList<Product>> getRetail_products() {
        return retail_products;
    }

    public void setRetail_products(int id, ArrayList<Product> retail_products) {
        this.retail_products.put(id,retail_products);
    }
}
