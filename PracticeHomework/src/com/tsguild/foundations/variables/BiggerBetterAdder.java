package com.tsguild.foundations.variables;

import java.util.Scanner;

public class BiggerBetterAdder {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int num1 = inputReader.nextInt();
        System.out.println("Enter a second number: ");
        int num2 = inputReader.nextInt();
        System.out.println("Enter a third number: ");
        int num3 = inputReader.nextInt();

        int sum = num1 + num2 + num3;

        System.out.println(num1 + " + " + num2 + " + " + num3 + " = ");

        System.out.println(sum);
//        System.out.println("Sum = " + sum);
    }
}
