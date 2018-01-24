package com.tsguild.foundations.variables;

import java.util.Scanner;

public class PassingTheTurningTest {
    public static void main(String[] args) {
        System.out.println("Hello there!");
        Scanner inputReader = new Scanner(System.in);

        System.out.println("What's your name? ");
        String usersName = inputReader.nextLine();

        System.out.println("Hi, " + usersName + "! What's your " +
                "favorite color?");
        String color = inputReader.nextLine();

        System.out.println("Huh, " + color + "? Mine's Electric Lime.");
        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + usersName + "?");
        String fruit = inputReader.nextLine();

        System.out.println("Really? " + fruit + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number? ");
        int number = inputReader.nextInt();

        System.out.println(number + " is a cool number. Mine's -7.");
        System.out.println("Did you know " + number + " * -7 is " + number*-7 + "? That's a cool number too!");

        System.out.println("Well, thanks for talking to me, " + usersName + "!");
    }
}
