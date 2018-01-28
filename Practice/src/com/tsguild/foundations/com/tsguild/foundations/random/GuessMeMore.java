package com.tsguild.foundations.com.tsguild.foundations.random;

import java.util.Random;
import java.util.Scanner;

public class GuessMeMore {
    public static void main(String[] args) {
        Random randomizer = new Random();
        Scanner scanner = new Scanner(System.in);
        String usersGuessString;
        int usersGuess;
        int randomInt = randomizer.nextInt(101) - 100;

        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it! ");
        System.out.println("Your guess: ");
        usersGuessString = scanner.nextLine();
        usersGuess = Integer.parseInt(usersGuessString);

        if(usersGuess == randomInt){
            System.out.println("\nWow, nice guess! That was it!");
        } else if(usersGuess < randomInt) {
            System.out.println("\nHa, nice try - too low! Try again!");
            System.out.println("Your guess: ");
            usersGuessString = scanner.nextLine();
            usersGuess = Integer.parseInt(usersGuessString);

            if(usersGuess == randomInt){
                System.out.println("\nWow, nice guess! That was it!");
            } else {
                System.out.println("Ha, nice try.");
            }
        } else if(usersGuess > randomInt){
            System.out.println("\nHa, nice try - too high! Try again!");
            System.out.println("Your guess: ");
            usersGuessString = scanner.nextLine();
            usersGuess = Integer.parseInt(usersGuessString);

            if(usersGuess == randomInt){
                System.out.println("\nWow, nice guess! That was it!");
            } else {
                System.out.println("Ha, nice try.");
            }
        }

    }
}
