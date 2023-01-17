package com.example.springDemo.api;

import java.util.ArrayList;
import com.example.springDemo.model.Product;

public interface Products {
     Product displayAllProducts(int productId,ArrayList<Product> productList);
     String addProducts(Product pro, ArrayList<Product> productList);

}
