package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userNumString;
        int userNum;

        System.out.println("How much units fizzing and buzzing do you need in your life? ");
        userNumString = scanner.nextLine();
        userNum = Integer.parseInt(userNumString);

        for(int i=0; i <= userNum; i++){
            if (i % 3 == 0 && i % 5 ==0 && i != 0){
                System.out.println("fizz buzz");
            }
            else if(i %3 == 0 && i != 0){
                System.out.println("fizz");
            } else if (i % 5 == 0 && i != 0){
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }

        System.out.println("TRADITION!!!!!");
    }
}
