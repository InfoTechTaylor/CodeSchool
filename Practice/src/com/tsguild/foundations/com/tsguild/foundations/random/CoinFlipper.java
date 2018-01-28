package com.tsguild.foundations.com.tsguild.foundations.random;

import java.util.Random;

public class CoinFlipper {
    public static void main(String[] args) {
        Random randomizer = new Random();
        boolean isHeads = randomizer.nextBoolean();
        String coinFlip;

        if(isHeads == true){
            coinFlip = "HEADS";
        } else{
            coinFlip = "TAILS";
        }

        System.out.println("Ready, Set, Flip....!!");
        System.out.println("You got " + coinFlip + "!");
    }
}
