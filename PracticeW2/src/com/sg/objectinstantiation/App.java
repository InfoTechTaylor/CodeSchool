package com.sg.objectinstantiation;

public class App {
    public static void main(String[] args) {
        double myPi = Adder.PI;

//        Adder myAdder = new Adder();

//        int sum = myAdder.add(5,4); // calling add off of an instance of a class
        int sum = Adder.add(5,4); // calling add with static method doesn't need to be off of an instantiation of the class

        System.out.println("The sum is " + sum);
    }
}
