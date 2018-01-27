package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class MiniZork {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.println("Go to the house, or open the mailbox?");


        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.println("Look inside or stick your hand in?");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.println("Run away or keep looking?");

                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");

                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you are alive. Possibly a wise choice.");
                }

            } else if (action.equals("stick your hand in")) {
                System.out.println("You get bitten by a grue.");
                System.out.println("Go look inside or run away? ");

                action = userInput.nextLine();

                if (action.equals("look inside")) {
                    System.out.println("You peer inside the mailbox.");
                    System.out.println("It's really very dark. So ... so very dark.");
                    System.out.println("Run away or keep looking?");

                    action = userInput.nextLine();

                    if (action.equals("keep looking")) {
                        System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                        System.out.println("You've been eaten by a grue.");

                    } else if (action.equals("run away")) {
                        System.out.println("You run away screaming across the fields - looking very foolish.");
                        System.out.println("But you are alive. Possibly a wise choice.");
                    }


                } // end mailbox option
            } // end stick your hand in
            }else if (action.equals("go to the house")) {
                    System.out.println("You go to the front door.");
                    System.out.println("You see a package.");
                    System.out.println("Open it or go inside?");
                    action = userInput.nextLine();

                    if(action.equals("open it")){
                        System.out.println("Turns out, opening other people's packages isn't a good idea.");
                        System.out.println("You've been eaten by a grue.");
                    } else if(action.equals("go inside")){
                        System.out.println("Inside the house is dark. So ... so very dark. ");
                        System.out.println("continue forward or run away?");

                        action = userInput.nextLine();

                        if (action.equals("continue forward")) {
                            System.out.println("You see some light moving in through a window in room ahead.");
                            System.out.println("Move toward the light or run away?");
                            action = userInput.nextLine();

                            if (action.equals("run away")){
                                System.out.println("You run away screaming across the fields - looking very foolish.");
                                System.out.println("But you are alive. Possibly a wise choice.");
                            } else if(action.equals("move toward the light")){
                                System.out.println("Turns out going into other people's houses is a bad idea.");
                                System.out.println("You've been eaten by a grue.");
                            }

                        } else if (action.equals("run away")) {
                            System.out.println("You run away screaming across the fields - looking very foolish.");
                            System.out.println("But you are alive. Possibly a wise choice.");
                        }

                    }
                } // end go to house

    } // end main
}// end class
