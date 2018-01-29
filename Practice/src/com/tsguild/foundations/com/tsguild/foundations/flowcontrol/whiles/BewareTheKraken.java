package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

import java.util.Random;
import java.util.Scanner;

public class BewareTheKraken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userAnswer, randomFish;
        String[] fishes = {"rainbow fish", "salmon", "tuna fish", "shark"};
        Random randomizer = new Random();
        int randomFishInt;

        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");

        int depthDivedInFt = 0;

        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
//        while(depthDivedInFt < 36200){
        while(true){  // if we switch to while true, the program will only end if we say we want to stop or we hit 20000 feet
            System.out.println("So far, we've swam " + depthDivedInFt + " feet");

            System.out.println("Want to stop (y/n)? ");
            userAnswer = scanner.nextLine();
            if(userAnswer.equals("y") || (userAnswer.equals("Y"))){
                System.out.println("Ok, let's go back up to the surface! ");
                break;
            }

            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }

            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
            randomFishInt = randomizer.nextInt(fishes.length);
            randomFish = fishes[randomFishInt];
            System.out.println("There is a " + randomFish + " over there!");
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}
