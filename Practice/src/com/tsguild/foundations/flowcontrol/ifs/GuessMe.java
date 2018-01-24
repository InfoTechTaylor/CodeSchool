package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class GuessMe{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secretNumber, usersGuess;
        String stringUsersGuess;

        // initialize secret number
        secretNumber = 47;

        // Get user's guess at number
        System.out.println("I've chosen a number. Betcha can't guess it! ");
        System.out.println("Your guess: ");
        stringUsersGuess = scanner.nextLine();
        usersGuess = Integer.parseInt(stringUsersGuess);

        // determine if user's guess is equal to secret number
        if(usersGuess == secretNumber){
            System.out.println("Wow, nice guess! That was it!");
        } else if (usersGuess < secretNumber) {
            System.out.println("Ha, nice try - too low! I chose " + secretNumber);
        } else {
            System.out.println("Too bad, way too high. I chose " + secretNumber);
        }

    }
}
