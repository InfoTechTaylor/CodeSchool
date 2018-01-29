package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

public class ForTimes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userNumString;
        int userNum=0;

        System.out.println("Which times table shall I recite? ");
        userNumString = scanner.nextLine();
        userNum = Integer.parseInt(userNumString);

        for(int i=1; i <= 15; i++){
            System.out.println(i + " * " + userNum + " is: " + (i*userNum) );
        }

    }
}
