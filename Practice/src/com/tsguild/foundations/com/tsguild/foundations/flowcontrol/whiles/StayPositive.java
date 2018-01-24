package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

import java.util.Scanner;

public class StayPositive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userNumber;
        String stringUserNumber;

        System.out.println("What number should I count down from? ");
        stringUserNumber = scanner.nextLine();
        userNumber = Integer.parseInt(stringUserNumber);
        System.out.println();
        System.out.println("Here goes!");
        System.out.println();


        int columnCounter = 0;
        while (userNumber >= 0){
            columnCounter++;
            if((columnCounter % 10) == 0) {
                System.out.println(userNumber);
                userNumber--;
            } else {
                System.out.print(userNumber + "\t");
                userNumber--;
            }
        }
        System.out.println("\nWhew, better stop there...!");
    }
}
