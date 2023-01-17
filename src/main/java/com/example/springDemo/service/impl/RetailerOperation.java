package com.example.springDemo.service.impl;

import com.example.springDemo.api.Retailers;
import com.example.springDemo.exception.UserDefinedException;
import com.example.springDemo.model.Product;
import com.example.springDemo.model.Retailer;
import com.example.springDemo.model.Wholesaler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;


@Service
public class RetailerOperation implements Retailers {

    @Override
    public Retailer displayRetailer(int retailerId, ArrayList<Retailer> retailersList) {
        try{
            if (retailersList.isEmpty()){throw new UserDefinedException("\n Sorry !!No retailer to Display\n\n");}
            for(Retailer n : retailersList){
                if(n.getRetail_id() ==retailerId){
                    return n;
                }
                System.out.println("\n <--------------------------------------------------> \n");
            }
        }catch(UserDefinedException ud){
            System.out.println(ud.getMessage());
        }
        return null;
    }

    @Override
    public String addRetailer(Retailer retailer, ArrayList<Retailer> retailersList) {
        retailersList.add(retailer);
        return "Successfully added the retailer";
    }

    @Override
    public String addProductToRetailer(int retId, int whoid, int proid, int quantity, int price, ArrayList<Wholesaler> wholesalerList, ArrayList<Retailer> retailersList) {
        boolean retailerNotFound = true;
        boolean productNotFound = true;
        boolean wholesalerNotFound = true;

        ArrayList<Product> productsList = new ArrayList<Product>();
        if (retailersList.isEmpty()){return("\n Sorry !!No wholesalers to Display\n\n");}
        if (quantity<0){return("\n Please enter a valid number for quantity.\n\n");}

        for(Retailer n : retailersList){
            if(n.getRetail_id() == retId){
                retailerNotFound=false;
                for(Wholesaler whole : wholesalerList){
                    if(whole.getWholesale_id()==whoid){
                        wholesalerNotFound =false;
                        for(Product pro:whole.getWholesale_products()) {
                            if (pro.proId == proid) {
                                productNotFound=false;
                                if (pro.stock < quantity) {
                                    return("\n Stocks are low. kindly try a small number\n\n");
                                }
                                NavigableMap<Integer,Integer> discount=new TreeMap<Integer, Integer>();


                                int billAmount =  pro.price*quantity;
//                                float discountPercentage = pro.discount.get(pro.discount.floorKey(quantity))/100f;
                                float discountPercentage =0;
                                float totalAfterOffer= billAmount - billAmount*discountPercentage;
                                float gstAmount = totalAfterOffer*(18/100f);
                                float grandBillAmount= totalAfterOffer+gstAmount;


                                System.out.println("\n\n\n\t\t\t<------Bill Amount----->");
                                System.out.println("\n\t\tProduct :: "+ pro.proName + "\n\t\tQuantity :: "+quantity);
//                                System.out.print("\n\t\tBill amount :: "+billAmount+"\n\t\tOffer percentage :: "+pro.discount.get(pro.discount.floorKey(quantity))+"%");
                                System.out.print("\n\t\tGST Percentage :: 18%\n\t\tGST Amount :: "+gstAmount);

                                System.out.print("\n\t\tGrand bill amount :: "+grandBillAmount);






                                Product pros = new Product(pro.proId, pro.proName, quantity, price,pro.gstPercentage);

                                productsList =n.getRetail_products().getOrDefault(whole.getWholesale_id(),productsList );
                                productsList.add(pros);

                                n.setRetail_products(whole.getWholesale_id(),productsList);

                                pro.stock = pro.stock - quantity;
                                return("Sucessfully Purchased");
                            }
                        }

                    }

                }

            }
        }
        if(wholesalerNotFound){ return("\n Sorry !!Wholesaler not found!\n\n"); }
        if(productNotFound){ return("\n Sorry !!Products not found!\n\n"); }
        if(retailerNotFound){ return("\n Sorry !!Retailers not found!\n\n"); }

        return("Error due to some reason. try again later");

    }
}


