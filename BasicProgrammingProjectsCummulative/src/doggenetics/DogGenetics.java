package doggenetics;

import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    static Scanner scanner = new Scanner(System.in);
    static Random randomizer = new Random();

    public static void main(String[] args) {

        String[] dogBreeds = {"St. Bernard", "Chihuahua", "Dramatic RedNosed Asian Pug", "Common Cur", "King Doberman"};
        String dogName;
        int randomNum, totalBreeds=dogBreeds.length;

        // get dog's name from user
        System.out.println("What is your dog's name? ");
        dogName = scanner.nextLine();

        // Output to user start of report
        System.out.println("Well then, I have this highly reliable report on " +
                            dogName + "'s prestigious background right here.\n");
        System.out.println(dogName + " is: \n");

        // calculate 5 random breed results and print to user
        int percentRemainder = 100;
        for(int i=0; i < totalBreeds; i++){

            if(i == (totalBreeds-1)){
                randomNum = percentRemainder;
            } else if(percentRemainder > 0){
                randomNum = getRandomInt(1, percentRemainder);
                percentRemainder-=randomNum;
            } else {
                randomNum = 0;
            }

            System.out.println(randomNum + "% " + dogBreeds[i]);
        }

        System.out.println("\nWow, that's QUITE the dog!");


    } // end main

    public static int getRandomInt(int lowerBound, int upperBound){
        upperBound = (upperBound - lowerBound) + 1;
        int randomInt = randomizer.nextInt(upperBound) + lowerBound;
        return randomInt;
    }

} // end class
