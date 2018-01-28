package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.whiles;

import java.util.Scanner;

public class RollerCoaster {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("We're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        String keepRiding = "y";
        int loopsLooped = 0;
        while (keepRiding.equals("y")) {
//          while (keepRiding.equals("n")) {  // if we check for "n" instead of "y", the code never enters the loop because keepRiding is initialized at y
            System.out.println("WHEEEEEEEEEEEEEeEeEEEEeEeeee.....!!!");
            System.out.print("Want to keep going? (y/n) :");
            keepRiding = userInput.nextLine();
            loopsLooped++; // there is not int in front of loopsLooped because it was declared as an int outside of the while before the loop
        }

        System.out.println("Wow, that was FUN!");
        System.out.println("We looped that loop " + loopsLooped + " times!!");
    }
}
