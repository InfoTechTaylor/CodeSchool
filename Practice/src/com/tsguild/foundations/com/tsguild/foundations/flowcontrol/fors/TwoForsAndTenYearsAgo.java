package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

public class TwoForsAndTenYearsAgo {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("What year would you like to count back from? ");
        int year = userInput.nextInt();

        for (int i = 0; i <= 10; i++) {  // range is 0 through 10
            System.out.println(i + " years ago would be " + (year - i));
        }

        System.out.println("\nI can count backwards using a different way too...");

        for (int i = year; i >= year - 20; i--) {  // range is the year through the year minus 10
            System.out.println( (year - i) + " years ago would be " + i);
        }
    }
}
