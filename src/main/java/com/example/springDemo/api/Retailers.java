package com.example.springDemo.api;

import com.example.springDemo.model.Retailer;
import com.example.springDemo.model.Wholesaler;

import java.util.ArrayList;

public interface Retailers {
    Retailer displayRetailer(int retailerId, ArrayList<Retailer> retailerList);
    String addRetailer(Retailer retailer, ArrayList<Retailer> retailerList);
    String addProductToRetailer(int retId, int whoid, int proid, int quantity, int price, ArrayList<Wholesaler> wholesalerList, ArrayList<Retailer> retailersList);
}
