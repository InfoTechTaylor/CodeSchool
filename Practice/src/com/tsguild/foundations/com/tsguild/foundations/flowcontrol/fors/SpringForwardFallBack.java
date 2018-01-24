package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

public class SpringForwardFallBack {
    public static void main(String[] args) {
        System.out.println("It's Spring...!");
        // for loop rang is 0-9
        for (int i = 1; i < 11; i++){
            System.out.print(i + ", ");
        }

        // for loop range is 10-1
        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--){
            System.out.print(i + ", ");
        }
    }
}
