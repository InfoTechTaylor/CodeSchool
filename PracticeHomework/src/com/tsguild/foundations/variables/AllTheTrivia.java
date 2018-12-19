package com.tsguild.foundations.variables;

import java.util.Scanner;

public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner newReader = new Scanner(System.in);

        System.out.println("1,024 Gigabytes is equal to one what?");
        String terabyte = newReader.nextLine();

        System.out.println("In our solar system which is the only planet that rotates clockwise? ");
        String planet = newReader.nextLine();

        System.out.println("The largest volcano ever discovered in our solar system is located on which planet? ");
        String planetVolcano = newReader.nextLine();

        System.out.println("What is the most abundant element in the earth's atmosphere? ");
        String element = newReader.nextLine();

        System.out.println("Wow, 1024 Gigabytes is a " + planet + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on " + terabyte + "!");
        System.out.println("That's amazing that " + planetVolcano + " is the most abundant element in the atmosphere...");
        System.out.println(element + " is the only planet that rotates clockwise, neat!");




    }

}
