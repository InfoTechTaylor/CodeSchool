package com.sg.RefactorIntoObjects;

import java.util.Scanner;

public class Factorizor {

    private static Scanner scanner = new Scanner(System.in);

    private int userNumber, perfectSum, i, factorCount;
    private String userNumberString;

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

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Factorizor.scanner = scanner;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getPerfectSum() {
        return perfectSum;
    }

    public void setPerfectSum(int perfectSum) {
        this.perfectSum = perfectSum;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getFactorCount() {
        return factorCount;
    }

    public void setFactorCount(int factorCount) {
        this.factorCount = factorCount;
    }

    public String getUserNumberString() {
        return userNumberString;
    }

    public void setUserNumberString(String userNumberString) {
        this.userNumberString = userNumberString;
    }



    public void runFactorizor() {
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


        isPerfect(userNumber, perfectSum);


        if(isPrime(perfectSum, userNumber)){
            System.out.println(userNumber + " is a prime number.");
        } else {
            System.out.println(userNumber + " is not a prime number.");
        }

        // print out factor count
        System.out.println("Total factor numbers: " + factorCount);



    }


}
