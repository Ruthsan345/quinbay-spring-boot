package com.example.springDemo.helper;
import com.example.springDemo.model.Product;

import com.example.springDemo.model.Product;
import com.example.springDemo.model.Retailer;
import com.example.springDemo.model.Wholesaler;
import org.apache.commons.csv.*;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CsvReader {

    public void readProductCsv(ArrayList<Product> productList) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get("/Users/ruthsan/Downloads/springDemo 2/csvfiles/products.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ){
            for (CSVRecord csvRecord : csvParser) {
                int proId = Integer.parseInt(csvRecord.get(0));
                String proName = csvRecord.get(1);
                int stock = Integer.parseInt(csvRecord.get(2));
                int price = Integer.parseInt(csvRecord.get(3));
                int gstPercentage = Integer.parseInt(csvRecord.get(4));
                Product products = new Product(proId, proName, stock, price, gstPercentage);
                productList.add(products);
            }}

    }

    public void readWholesalerCsv( ArrayList<Wholesaler> wholsalerList) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get("/Users/ruthsan/Downloads/springDemo 2/csvfiles/wholesalers.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ){
            for (CSVRecord csvRecord : csvParser) {
                int id = Integer.parseInt(csvRecord.get(0));
                String name = csvRecord.get(1);
                ArrayList<Product> wholesalersProductList = new ArrayList<Product>();
                Wholesaler wholesaler = new Wholesaler(id, name, wholesalersProductList);
                wholsalerList.add(wholesaler);
            }}

    }

    public void readRetailerCsv( ArrayList<Retailer> retailerList) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get("/Users/ruthsan/Downloads/springDemo 2/csvfiles/retailer.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ){
                for (CSVRecord csvRecord : csvParser) {
                    int id = Integer.parseInt(csvRecord.get(0));
                    String name = csvRecord.get(1);
                    HashMap<Integer,ArrayList<Product> > retailersProductList=new HashMap<Integer,ArrayList<Product> >();
                    Retailer retailer = new Retailer(id, name, retailersProductList );
                    retailerList.add(retailer);
                }

        }

    }
}
