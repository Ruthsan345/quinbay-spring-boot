package com.example.springDemo.service.impl;

import com.example.springDemo.api.Car;
import com.example.springDemo.model.CarDetails;
import org.springframework.stereotype.Service;


@Service
public class CarOperation implements Car {

    @Override
    public CarDetails getCarDetails(int carId){
        CarDetails carDetail = new CarDetails(111, "Hilux","Toyota");
        if(carDetail.getCarId() ==carId){
            return carDetail;
        }else{
            return null;
        }
    }

    @Override
    public CarDetails addCarDetails() {
        return null;
    }



}
