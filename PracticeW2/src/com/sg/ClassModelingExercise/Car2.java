package com.sg.ClassModelingExercise;

public class Car2 {
    private String carType;
    private String color;
    private int topSpeed;
    private String wheelType;
    private int accelleration; // scale of 1-10
    private int handling; // scale of 1-10
    private int currentSpeed;

    public Car2(String carType, String color){
        this.carType = carType;
        this.color = color;
    }

    public void accelerate(){

    }

    public void deccelerate(){

    }

    public void useBreak(){

    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public int getAccelleration() {
        return accelleration;
    }

    public void setAccelleration(int accelleration) {
        this.accelleration = accelleration;
    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }
}
