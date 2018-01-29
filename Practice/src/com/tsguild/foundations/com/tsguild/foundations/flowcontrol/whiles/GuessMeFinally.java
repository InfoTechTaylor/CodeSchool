package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

import java.util.Random;
import java.util.Scanner;

public class GuessMeFinally {

    static Random randomizer = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int usersGuess;
        boolean guessCorrect = false;
        int randomInt = randomizer.nextInt(101) - 100;

        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it! ");
        usersGuess = getUserNumber();

        if(usersGuess == randomInt){
            System.out.println("Wow, nice guess! That was it!");
            guessCorrect = true;
        }

        while(guessCorrect == false){

            if(usersGuess == randomInt){
                System.out.println("Finally, it's about time you got it!");
                guessCorrect = true;
            } else {
                if(usersGuess < randomInt){
                    System.out.println("Ha, nice try - too low! Try again! ");
                    usersGuess = getUserNumber();
                } else if (usersGuess > randomInt){
                    System.out.println("Too bad, way too high. Try again!");
                    usersGuess = getUserNumber();
                }
            }
        }

    }

    public static int getUserNumber(){
        String usersGuessString;
        int usersGuess;

        System.out.println("Your guess: ");
        usersGuessString = scanner.nextLine();
        usersGuess = Integer.parseInt(usersGuessString);

        return usersGuess;
    }
}
