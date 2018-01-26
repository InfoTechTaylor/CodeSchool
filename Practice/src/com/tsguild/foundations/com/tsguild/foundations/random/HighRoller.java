package com.tsguild.foundations.com.tsguild.foundations.random;

import java.util.Random;
import java.util.Scanner;

public class HighRoller {
    public static void main(String[] args) {
        Random diceRoller = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many sides does the dice have? ");
        String numSidesString = scanner.nextLine();
        int numSides = Integer.parseInt(numSidesString);
        numSides++;

        int rollResult = diceRoller.nextInt(numSides) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if(rollResult == 1){
            System.out.println("You rolled a critical failure! Ouch.");
        } else if (rollResult == numSides){
            System.out.println("You rolled a critical! Nice Job!");
        }
    }
}
