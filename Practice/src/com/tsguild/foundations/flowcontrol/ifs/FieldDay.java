package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class FieldDay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lastName;

        // get last name of user
        System.out.println("What's your last name? ");
        lastName = scanner.nextLine();



         //based on last name, provide team name
        if(lastName.compareTo("Baggins") < 0){
            System.out.println("Aha! You're on team 'Red Dragons'");
        } else if (((lastName.compareTo("Baggins")) > 0) &&  (lastName.compareTo("Dresden") < 0)){
            System.out.println("Aha! You're on team 'Dark Wizards'");
        } else if (((lastName.compareTo("Dresden")) > 0) &&  (lastName.compareTo("Howl") < 0)){
            System.out.println("Aha! You're on team 'Moving Castles'");
        } else if (((lastName.compareTo("Howl")) > 0) &&  (lastName.compareTo("Potter") < 0)){
            System.out.println("Aha! You're on team 'Golden Snitches'");
        } else if (((lastName.compareTo("Potter")) > 0) &&  (lastName.compareTo("Vimes") < 0)){
            System.out.println("Aha! You're on team 'Night Guards'");
        } else if ((lastName.compareTo("Vimes")) < 0){
            System.out.println("Aha! You're on team 'Black Holes'");
        }

        System.out.println("Good luck in the games!");

    } // end main
} // end FieldDay
