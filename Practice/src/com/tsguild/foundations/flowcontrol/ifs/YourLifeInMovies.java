package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class YourLifeInMovies {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String name, stringBirthYear;
        int birthYear;

        System.out.println("Hey, let's play a game! What's your name? ");
        name = scanner.nextLine();
        System.out.println();

        System.out.println("Ok, " + name + ", when were you born? ");
        stringBirthYear = scanner.nextLine();
        birthYear = Integer.parseInt(stringBirthYear);

        System.out.printf("Well " + name + "...");

        if(birthYear < 2005){
            System.out.println("Pixar's 'Up' came out half a decade ago.");
        }

        if(birthYear < 1995){
            System.out.println("The first Harry Potter came out over 15 years ago.");
        }

        if(birthYear < 1985){
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }

        if(birthYear < 1975){
            System.out.println("The original Jurassic Park release is closer to the lunar landing, than today.");
        }

        if(birthYear < 1965){
            System.out.println("The MASH has been around for almost half a century!");
        }



    }
}
