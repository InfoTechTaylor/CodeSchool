package com.tsguild.foundations.variables;

import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter a noun: ");
        String noun1 = reader.nextLine();
        System.out.println("Enter an adjective: ");
        String adjective2 = reader.nextLine();
        System.out.println("Enter another noun: ");
        String noun3 = reader.nextLine();
        System.out.println("Enter a number: ");
        int number4 = reader.nextInt();
        reader.nextLine();
        System.out.println("Enter another adjective: ");
        String adjective5 = reader.nextLine();
        System.out.println("Enter a plural noun: ");
        String pluralNoun6 = reader.nextLine();
        System.out.println("Enter another plural noun: ");
        String pluralNoun7 = reader.nextLine();
        System.out.printf("Enter another plural noun: ");
        String pluralNoun8 = reader.nextLine();
        System.out.println("Enter a verb in present tense: ");
        String presentVerb9 = reader.nextLine();
        System.out.println("Enter the same verb but past tense: ");
        String pastVerb10 = reader.nextLine();

        System.out.println(noun1 + ": the " + adjective2 + " frontier. These are the voyages of the " +
                "starship " + noun3 + ". Its " + number4 + "-year mission: to explore strange " +
                adjective5 + " " + pluralNoun6 + ", to seek out " + adjective5 + " " +
                pluralNoun7 + " and " + adjective5 + " " + pluralNoun8 + ", to boldly " +
                presentVerb9 + " where no one has " + pastVerb10 + " before.");
    }
}
