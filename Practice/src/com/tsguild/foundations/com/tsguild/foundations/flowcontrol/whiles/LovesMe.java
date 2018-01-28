package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

public class LovesMe {
    public static void main(String[] args) {
        System.out.println("Well here goes nothing...");

        int petalCount = 1;
        while(petalCount <= 34){ // I chose a while instead of a do while because I only want it to execute if there are petals.
            if(petalCount % 2 != 0){
                System.out.println("It loves me NOT!");
            } else {
                System.out.println("It LOVES me!");

                if(petalCount == 34){
                    System.out.println("I knew it! It LOVES ME!");
                }
            }

            petalCount++;
        }
    }
}
