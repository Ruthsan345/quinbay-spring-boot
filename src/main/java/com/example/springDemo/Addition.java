package com.example.springDemo;

import org.springframework.stereotype.Service;

@Service
public class Addition {
    public int add(int a , int b){
        return a+b;
    }
}
