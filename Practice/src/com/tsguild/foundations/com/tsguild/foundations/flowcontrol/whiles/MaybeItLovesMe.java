package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

import java.util.Random;

public class MaybeItLovesMe {
    public static void main(String[] args) {
        Random randomizer = new Random();
        int numPetals = randomizer.nextInt(90) + 13;
        System.out.println("Well here goes nothing...");

        int petalCount = 1;
        boolean lovesMe = true;
        while(petalCount <= numPetals){ // I chose a while instead of a do while because I only want it to execute if there are petals.
            if(petalCount % 2 != 0){
                System.out.println("It loves me NOT!");
                lovesMe = false;
            } else {
                System.out.println("It LOVES me!");
                lovesMe = true;
            }

            petalCount++;
        }

        if(lovesMe == true){
            System.out.println("Oh, wow! It really LOVES me!");
        } else {
            System.out.println("Awwww, bummer.");
        }
    }
}
