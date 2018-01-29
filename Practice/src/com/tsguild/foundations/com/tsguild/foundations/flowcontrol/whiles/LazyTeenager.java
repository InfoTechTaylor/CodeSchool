package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

import java.util.Random;

public class LazyTeenager {
    public static void main(String[] args) {
        int numScolds=1;
        int chanceOfCleaning=0;
        Random randomizer = new Random();

        do{
            System.out.println("Clean your room!! (x" + numScolds + ")");
            numScolds++;
            chanceOfCleaning+=5;
            int randomPercent = (randomizer.nextInt(15) +1) *5;

            if(chanceOfCleaning == randomPercent){
                System.out.println("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
            }

            if(numScolds == 15){
                System.out.println("That's IT, I'm doing it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                break;
            }

        } while(true);
    }
}
