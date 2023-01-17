package com.example.springDemo.model;

public class CarDetails {
    int carId;
    String carName;
    String carMake;

    public CarDetails(){}
    public CarDetails(int carId, String carName, String carMake){
        this.carId =carId;
        this.carName = carName;
        this.carMake=carMake;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }



}
