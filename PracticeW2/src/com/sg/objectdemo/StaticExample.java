package com.sg.objectdemo;

import java.util.Scanner;

public class StaticExample {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        // scanner relies on the state of System.in to run, information is needed

        System.out.println("Enter your age: ");
        String ageString = userInput.nextLine();
        int age = Integer.parseInt(ageString); // parseInt is a static method because we can call it without an instance of Integer class



    }
}
