package com.example.springDemo.service.impl;

import com.example.springDemo.api.Products;
import com.example.springDemo.exception.UserDefinedException;
import com.example.springDemo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class ProductOperation implements Products {

    @Override
    public Product displayAllProducts(int productId,ArrayList<Product> productList){
        try{
            if (productList.isEmpty()){throw new UserDefinedException("\n Sorry !!No Products to Display\n\n");}
            for(Product n : productList){
               if(n.proId ==productId){
                   return n;
               }
            }

        }catch(UserDefinedException ud){
            System.out.println(ud.getMessage());
        }
        return null;
    }


    @Override
    public String addProducts(Product pro,ArrayList<Product> productList){
            productList.add(pro);
            return "Successfully added the product";
    }

}
