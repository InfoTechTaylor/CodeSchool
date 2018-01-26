package com.tsguild.foundations.com.tsguild.foundations.com.tsguild.foundations.basics.arrays;

import java.util.Random;

public class HiddenNuts {

    public static void main(String[] args) {

        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");

        // Nut finding code should go here!
        for(int i=0; i < hidingSpots.length; i++){
            if(hidingSpots[i] != null || hidingSpots[i] == "Nuts"){
                System.out.println("Found it! It's in spot# " + i);
            }
        }
    }
}
