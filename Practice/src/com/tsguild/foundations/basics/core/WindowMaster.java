package com.tsguild.foundations.basics.core;

import java.util.Scanner;

public class WindowMaster {

    public static void main(String[] args) {
        // Declare variables for height and width
        float height;
        float width;
        // Declare String variables to hold the user's height and width
        // input
//        String stringHeight;
//        String stringWidth;

        // Declare other variables
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;

        // Declare and initialize our Scanner
        Scanner sc = new Scanner(System.in);

        // Get input from user
//        System.out.println("Please enter window height:");
//        stringHeight = sc.nextLine();
//        System.out.println("Please enter window width:");
//        stringWidth = sc.nextLine();
        height= readValue("Please enter the window height: ");
        width = readValue("Please enter the window width: ");

        // Convert String values of height and width to floats
//        height = Float.parseFloat(stringHeight);
//        width = Float.parseFloat(stringWidth);

        // Calculate area of window
        areaOfWindow = height * width;

        // Calculate the perimeter of the window
        perimeterOfWindow = 2 * (height + width);

        // Calculate total cost - use hard coded for material cost
        cost = ((3.50f * areaOfWindow) + (2.25f * perimeterOfWindow));

        System.out.println("Window height = " + height);
        System.out.println("Window width = " + width);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Total Cost = " + cost);
    }

    public static float readValue(String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String stringInput = sc.nextLine();
        float input = Float.parseFloat(stringInput);

        return input;
    }
}
