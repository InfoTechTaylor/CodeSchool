package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class KnockKnock {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();

        if(nameGuess.equals("Marty McFly")){ // when changed to == instead of .equals() this statement doesn't work
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future.");

        }else {
            System.out.println("Dude, do I -look- like " + nameGuess);
        }
    }
}
