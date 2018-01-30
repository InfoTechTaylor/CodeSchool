package com.sg.RefactorIntoObjects;

import java.util.Scanner;

public class Factorizor {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int userNumber, perfectSum = 0, i=1, factorCount = 1;
        String userNumberString;

        // get number from user
        System.out.println("What number would you like to factor? ");
        userNumberString = scanner.nextLine();
        userNumber = Integer.parseInt(userNumberString);

        // print user's number back to them
        System.out.println("The factors of " + userNumber + " are: ");

        // determine userNumber factors loop
        while(i < userNumber) {
            if(userNumber % i ==0) {
                System.out.println(i);
                perfectSum += i;
                factorCount++;
            }

            i++;

        } // end while

        // determine if userNumber is perfect
//        if(perfectSum == userNumber){
//            System.out.println(userNumber + " is a perfect number.");
//        } else {
//            System.out.println(userNumber + " is not a perfect number. ");

        isPerfect(userNumber, perfectSum);

        // determine if userNumber is prime
//        if(perfectSum == 1 || userNumber == 1){
//            System.out.println(userNumber + " is a prime number. ");
//        } else {
//            System.out.println(userNumber + " is not a prime number.");
//        }
        //boolean prime = isPrime(perfectSum, userNumber);

        if(isPrime(perfectSum, userNumber)){
            System.out.println(userNumber + " is a prime number.");
        } else {
            System.out.println(userNumber + " is not a prime number.");
        }

        // print out factor count
        System.out.println("Total factor numbers: " + factorCount);



    }

    public static void isPerfect(int num, int sum) {
        if(sum == num) {
            System.out.println(num + " is a perfect number.");

        } else {
            System.out.println(num + " is not a perfect number.");

        }
    }

    public static boolean isPrime(int sum, int num){
        if (sum == 1 || num == 1){
            //System.out.println(num + " is a prime number.");
            return true;
        } else {
            //System.out.println(num + " is not a prime number.");
            return false;
        }
    }
}
