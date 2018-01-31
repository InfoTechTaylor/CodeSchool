package com.sg.RefactorIntoObjects;

import java.util.Random;
import java.util.Scanner;

public class LuckySevens {

    public void runLuckySevens(){
        Scanner scanner = new Scanner(System.in);

        int dollarAmount, maxAmount, dice1, dice2, totalRollCount = 0, maxRollCount = 1;
        String stringDollarAmount;

        // validate user's input is greater than 0 dollars
        do {
            // get initial bet amount from user and convert to an integer
            System.out.println("How many dollars do you want to bet? ");
            stringDollarAmount = scanner.nextLine();
            dollarAmount = Integer.parseInt(stringDollarAmount);

            // set the max amount
            maxAmount = dollarAmount;

            // give output to user if they do not enter enough money to play the game
            if (dollarAmount <= 0) {
                System.out.println("You need more than zero dollars to play Lucky Sevens!");
            } else {
                System.out.println("Good luck playing! Hope you win!!");
            }

        } while (dollarAmount <= 0);


        // play lucky sevens
        while(true){
            if(dollarAmount > 0){

                // roll the dice
                dice1 = new Random().nextInt(6) + 1; // plus 1 gets rid of the index of zero
                dice2 = new Random().nextInt(6) + 1;

                // increment total roll count to keep track of total rolls
                totalRollCount++;

                // determine if dice roll is equal to 7
                if ((dice1 + dice2) == 7){
                    dollarAmount += 4;

                    // update current max roll count and max amount variables
                    if(dollarAmount > maxAmount){
                        maxAmount = dollarAmount;
                        maxRollCount = totalRollCount;
                    }

                } else {
                    dollarAmount --;
                }

            } else {
                // user loses, no more money to play
                System.out.println("You are broke after " + totalRollCount + " rolls.");
                System.out.println("You should have quit after " + maxRollCount + " roll(s) when you had $" + maxAmount + ".");
                break;
            }
        }
    }
}
