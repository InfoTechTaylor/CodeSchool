package com.tsguild.foundations.variables;

import java.util.Scanner;

public class DoltBetter {

    public static void main(String[] args) {
        Scanner newReader = new Scanner(System.in);
        System.out.println("How many miles can you run? ");
        int miles = newReader.nextInt();
        int moreMiles = (miles*2) + 1;
        System.out.println("I can do " + moreMiles + " miles!");

        System.out.println("How many hot dogs can eat? ");
        int hotdogs = newReader.nextInt();
        int moreHotdogs = (hotdogs * 2) + 1;
        System.out.println("I can eat " + moreHotdogs + " hot dogs!");

        System.out.println("How many languages do you know? ");
        int languages = newReader.nextInt();
        int moreLanguages = (languages * 2) + 1;
        System.out.println("I can speak " + moreLanguages + " languages!");


    }
}
