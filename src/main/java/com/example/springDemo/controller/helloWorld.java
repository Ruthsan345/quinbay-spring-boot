package com.example.springDemo.controller;


import com.example.springDemo.Addition;

import com.example.springDemo.api.Products;
import com.example.springDemo.api.Retailers;
import com.example.springDemo.api.Wholesalers;
import com.example.springDemo.helper.CsvReader;
import com.example.springDemo.model.Product;

import com.example.springDemo.model.Retailer;
import com.example.springDemo.model.Wholesaler;
import com.example.springDemo.service.impl.CarOperation;
import com.example.springDemo.service.impl.ProductOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RequestMapping("api")
@RestController()
public class helloWorld {
    @Autowired Products productOp;
    @Autowired Wholesalers wholesalerOp;
    @Autowired Retailers retailerOp;
    @Autowired CsvReader csvRead;

    ArrayList<Product> productsList = new ArrayList<Product>();
    ArrayList<Wholesaler> wholesalersList = new ArrayList<Wholesaler>();
    ArrayList<Retailer> retailerList = new ArrayList<Retailer>();

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws IOException {
        csvRead.readProductCsv(productsList);
        System.out.print("<--------------Product read from CSV-------------->");
        csvRead.readWholesalerCsv(wholesalersList);
        System.out.print("\n<--------------Wholesaler read from CSV-------------->");
        csvRead.readRetailerCsv(retailerList);
        System.out.print("\n<--------------Retailer fetched from CSV-------------->");
    }


//    GET Methods

    @GetMapping("/")
    public String index(){
        return "Hello world";
    }

    @GetMapping("/getProduct")
    public Product getProduct(@RequestParam int productId) {
        return productOp.displayAllProducts(productId, productsList);
    }

    @GetMapping("/getWholesaler")
    public Wholesaler getWholesaler(@RequestParam int wholesalerId) {
        return wholesalerOp.displayWholesaler(wholesalerId,wholesalersList);
    }


    @GetMapping("/getRetailer")
    public Retailer getRetailer(@RequestParam int retailerId) {
        return retailerOp.displayRetailer(retailerId,retailerList);
    }

    @PostMapping("/addProducts")
    @ResponseStatus(HttpStatus.CREATED)
    public String addProducts(@RequestBody Product prod){
        return productOp.addProducts(prod, productsList );
    }

    @PostMapping("/addWholesalers")
    @ResponseStatus(HttpStatus.CREATED)
    public String addWholesaler(@RequestBody Wholesaler wholesaler){
        return wholesalerOp.addWholesaler(wholesaler, wholesalersList);
    }

    @PostMapping("/addRetailers")
    @ResponseStatus(HttpStatus.CREATED)
    public String addRetailer(@RequestBody Retailer retailer){
        return retailerOp.addRetailer(retailer, retailerList);
    }


    //    PUT Methods

    @PutMapping("/allocateProductToWholesaler")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String allocateProductToWholesaler(@RequestParam int wholesalerId, @RequestParam int proid, @RequestParam  int quantity, @RequestParam  int price){
        return wholesalerOp.allocateProductToWholesaler(wholesalerId,proid,quantity,price,productsList, wholesalersList);
    }

    @PutMapping("/allocateProductToRetailer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String allocateProductToRetailer(@RequestParam int retailerId,@RequestParam int wholesalerId, @RequestParam int proid, @RequestParam  int quantity, @RequestParam  int price){
        return retailerOp.addProductToRetailer(retailerId,wholesalerId,proid,quantity,price, wholesalersList,retailerList);
    }



//    @GetMapping("/name")
//    public String name(@RequestParam String name, @RequestParam("location") String locations){
//        return "Hello world "+name+" "+locations;
//    }
//
//    @GetMapping("/name/{employeeName}")
//    public String name(@RequestParam String name, @RequestParam("location") String locations, @PathVariable String employeeName){
//        return "Hello world "+name+" "+employeeName;
//    }
//
//    @PostMapping("/users")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ArrayList <User> registerUserCredential(@RequestBody ArrayList<User> user){
//        for(User userDetails: user){
//            System.out.println("User ID: "+userDetails.getUserName());
//            System.out.println("User ID: "+userDetails.getPassword());
//        }
//     return user;
//    }
//
//    @GetMapping("/greeting")
//    public ResponseEntity<String> greeting(@RequestHeader("my-number") int myNumber) {
//        // code that uses the language variable
//        return new ResponseEntity<String>("HI Got the Response"+myNumber, HttpStatus.OK);
//    }
//
//    @GetMapping("/addition")
//    public ResponseEntity<Integer> addition(@RequestParam int a, @RequestParam int b) {
//        return new ResponseEntity<Integer>(ad.add(a,b), HttpStatus.OK);
//    }

//    @GetMapping("/cardetails")
//    public CarDetails cardetail(@RequestParam int a) {
//        return c.getCarDetails(a);
//    }



}
