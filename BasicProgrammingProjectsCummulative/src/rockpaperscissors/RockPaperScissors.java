package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static Scanner scanner = new Scanner(System.in);
    static Random randomizer = new Random();

    static int userWins, computerWins;

    public static void main(String[] args) {
        String roundsToPlayString, userChoiceString, computerChoiceString, playAgainString;
        int roundsToPlay, numTies=0, userChoice, computerChoice;
        boolean playAgain = true;

        do{
            // Ask user how many rounds to play
            System.out.println("How many rounds would you like to play Rock, Paper, Scissors? (Choose a number 1-10) ");
            roundsToPlayString = scanner.nextLine();
            roundsToPlay = Integer.parseInt(roundsToPlayString);

            // end game if user enters a number outside range # 1-10
            if((roundsToPlay > 10) || (roundsToPlay < 1)){
                playAgain = false;
                System.out.println("You did not enter a valid number of rounds. You must enter a number 1 through 10. Exiting game!");
                break;
            }

            // loop through game as long as i is less than roundsToPlay
            for(int i=0; i < roundsToPlay; i++){

                do {
                    // get user's choice of rock, paper or scissors from user
                    System.out.println("Choose 1) rock, 2) paper, or 3) scissors. Enter the # of your choice: ");
                    userChoiceString = scanner.nextLine();
                    userChoice = Integer.parseInt(userChoiceString);
                } while(userChoice < 1 || userChoice > 3); // validate user enters 1,2, or 3

                // generate computer's choice using random
                computerChoice = randomizer.nextInt(3) + 1;
                computerChoiceString = String.valueOf(computerChoice);

                // increment if there is a tie, else call method to see if winner or loser
                if(userChoice == computerChoice){
                    numTies++;
                    System.out.println("Computer made the same choice. This round is a tie!");
                } else {
                    // get winner
                    boolean userWins = isUserWinner(userChoiceString, computerChoiceString);

                    //print winner
                    if(userWins == true){
                        System.out.println("Computer picked " + computerChoice + ". You won this round!");
                    } else {
                        System.out.println("Computer picked " + computerChoice + ". Sorry, computer won this round!");
                    }
                }
            }

            // print out game stats
            System.out.println("Total ties: " + numTies);
            System.out.println("Your total wins: " + userWins);
            System.out.println("Computer's total wins: " + computerWins);

            // calculate who wins
            if(userWins > computerWins){
                System.out.println("Congratulations, you're the winner!\n");
            } else if (userWins == computerWins){
                System.out.println("It's a tie! ");
            } else{
                System.out.println("Sorry, you lose!\n");
            }

            // see if user wants to play again
            System.out.println("Want to play again? Enter y for yes and n for no: ");
            playAgainString = scanner.nextLine();

            if(playAgainString.equals("n") || playAgainString.equals("N")){
                playAgain = false;
                System.out.println("Thanks for playing!");
            } else {
                userWins = 0;
                computerWins = 0;
            }

        } while(playAgain == true);


    } // end main

    public static boolean isUserWinner(String userChoice, String computerChoice){

        // rock = 1, paper = 2, scissors = 3
        // example: 12 means user picked rock and computer picked paper with the concatenated strings
        switch (userChoice + computerChoice) {
            case "12":
            case "23":
            case "31":
                // computer wins
                computerWins++;
                return false;
                //break;
            case "13":
            case "21":
            case "32":
                // user wins
                userWins++;
                return true;
                //break;
            default:
                return true;

        } // end switch

    } // end main
} // end class
