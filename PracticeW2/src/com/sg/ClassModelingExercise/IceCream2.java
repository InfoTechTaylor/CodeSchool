package com.sg.ClassModelingExercise;

public class IceCream2 {
    // Model ice cream as if the class were to be part of the stocking system at a grocery store.

    private String brand;
    private String flavor;
    private String size;
    private double price;
    private boolean isActive;

    public IceCream2(String brand, String flavor, String size, double price) {
        this.brand = brand;
        this.flavor = flavor;
        this.size = size;
        this.price = price;
    }

    public IceCream2(){
        // default constructor
    }

//    public void updatePrice(double newPrice){
//        this.setPrice(newPrice);
////        this.price = newPrice;
//    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
