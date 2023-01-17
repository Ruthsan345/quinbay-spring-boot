package com.example.springDemo.api;

import com.example.springDemo.model.CarDetails;
import org.springframework.stereotype.Service;


public interface Car {
    CarDetails getCarDetails(int carId);
    CarDetails addCarDetails();
}
