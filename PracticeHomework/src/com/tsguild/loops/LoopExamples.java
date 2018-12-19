package com.tsguild.loops;

import java.util.Random;
import java.util.Scanner;

public class LoopExamples {
    public static void main(String[] args) {
//        for (int i = 5; i < 21; i+=5){
//            System.out.println(i);
//        }

//        int i = 1;
//
//        while(i < 6) {  // while loop shines when we are not sure how many times it will run
//            System.out.println(i);
//            i++;
//        }

//        Random rGen = new Random();
////        int randomNum = rGen.nextInt(10) + 1;
////
////        while(randomNum < 8){
////            System.out.println(randomNum);
////            randomNum = rGen.nextInt(10) + 1;
////        }

//        int i = 1;
//        do {
//            System.out.println(i);
//            i++;
//        } while (i < 6);


        Scanner sc = new Scanner(System.in);
        int userNumber;
        String userNumberString;

        do {
            System.out.println("Enter a number between 1 and 20: ");
            userNumberString = sc.nextLine();
            userNumber = Integer.parseInt(userNumberString);

        } while (userNumber < 1 || userNumber > 20);

        System.out.println("Thank you!!! Your number was: " + userNumber);

    }
}
