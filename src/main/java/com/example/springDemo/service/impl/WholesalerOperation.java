package com.example.springDemo.service.impl;


import com.example.springDemo.api.Wholesalers;
import com.example.springDemo.exception.UserDefinedException;
import com.example.springDemo.model.Product;
import com.example.springDemo.model.Wholesaler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

@Service
public class WholesalerOperation implements Wholesalers {
    @Override
    public Wholesaler displayWholesaler(int wholesaleId,ArrayList<Wholesaler> wholesalerList){
        try{
            if (wholesalerList.isEmpty()){throw new UserDefinedException("\n Sorry !!No wholesaler to Display\n\n");}
            for(Wholesaler n : wholesalerList){
                    if(n.getWholesale_id() ==wholesaleId){
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
    public String addWholesaler(Wholesaler wholesaler, ArrayList<Wholesaler> wholesalerList) {
        wholesalerList.add(wholesaler);
        return "Successfully added the retailer";
    }

    @Override
    public String allocateProductToWholesaler(int wholesalerId, int proid, int quantity, int price, ArrayList<Product> productsList, ArrayList<Wholesaler> wholesalerList) {

            boolean wholesalerNotFound = true;
            boolean productNotFound = true;
            if (wholesalerList.isEmpty()){return("\n Sorry !!No wholesalers to Display\n\n");}
            if (quantity<0){return ("\n Please enter a valid number for quantity.\n\n");}

            for(Wholesaler n : wholesalerList){
                if(n.getWholesale_id() == wholesalerId){
                    wholesalerNotFound =false;
                    for(Product pro : productsList){
                        if(pro.proId== proid){
                            productNotFound= false;
                            if (pro.stock< quantity){return("\n Stocks are low. kindly try a small number\n\n");}

                            int billAmount =  pro.price*quantity;
//                            float discountPercentage = pro.discount.get(pro.discount.floorKey(quantity))/100f;
                            float discountPercentage=0;
                            float totalAfterOffer= billAmount - billAmount*discountPercentage;
                            float gstAmount = totalAfterOffer*(18/100f);
                            float grandBillAmount= totalAfterOffer+gstAmount;

                            System.out.print("\ngrand bill amount :: "+grandBillAmount);
                            Product pros = new Product(pro.proId, pro.proName, quantity, price,pro.gstPercentage);
                            n.setWholesale_products(pros);
                            pro.stock=pro.stock-quantity;

                            System.out.println("\n\n\n\t\t\t<------Bill Amount----->");
                            System.out.println("\n\t\tProduct :: "+ pro.proName + "\n\t\tQuantity :: "+quantity);
//                            System.out.print("\n\t\tBill amount :: "+billAmount+"\n\t\tOffer percentage :: "+pro.discount.get(pro.discount.floorKey(quantity))+"%");
                            System.out.print("\n\t\tGST Percentage :: 18%\n\t\tGST Amount :: "+gstAmount);
                            System.out.print("\n\t\tGrand bill amount :: "+grandBillAmount);

                            return("Sucessfully Purchased");

                        }
                    }

                }
            }
            if(wholesalerNotFound){ return("\n Sorry !!Wholesaler not found!\n\n"); }
            if(productNotFound){ return ("\n Sorry !!Products not found!\n\n"); }

            System.out.println("\n <--------------------------------------------------> \n");

         return("Exited due to an error");
    }


}
