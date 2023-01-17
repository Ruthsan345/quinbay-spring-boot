package com.example.springDemo.api;


import com.example.springDemo.model.Product;
import com.example.springDemo.model.Wholesaler;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface Wholesalers {
    Wholesaler displayWholesaler(int wholeSalerId, ArrayList<Wholesaler> wholesalerList);
    String addWholesaler(Wholesaler wholesaler, ArrayList<Wholesaler> wholesalerList);
    String allocateProductToWholesaler(int wholesalerId, int proid, int quantity, int price, ArrayList<Product>productsList, ArrayList<Wholesaler> wholesalerList);
}
