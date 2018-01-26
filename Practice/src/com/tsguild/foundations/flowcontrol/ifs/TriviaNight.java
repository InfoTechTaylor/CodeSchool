package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class TriviaNight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        System.out.println();

        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code\t\t\t\t 2) Assembly Language");
        System.out.println("1) C#\t\t\t\t\t\t 4) Machine Code");
        String answer1String = scanner.nextLine();
        int answer1 = Integer.parseInt(answer1String);
        System.out.println("YOUR ANSWER: " + answer1);

        System.out.println("SECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper\t\t\t\t2) Alan Turing");
        System.out.println("3) Charles Babbage\t\t\t4) Larry Page");
        String answer2String = scanner.nextLine();
        int answer2 = Integer.parseInt(answer2String);
        System.out.println("YOUR ANSWER: " + answer2);

        System.out.println("LAST QUESTION!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity \t\t\t\t 2) The Battlestar Galactica");
        System.out.println("3) The USS Enterprise \t\t\t\t 4) The Millennium Falcon");
        String answer3String = scanner.nextLine();
        int answer3 = Integer.parseInt(answer3String);
        System.out.println("YOUR ANSWER: " + answer3);

        int correctCount = 0;
        if(answer1 == 4){
            correctCount++;
        }
        if(answer2 == 2){
            correctCount++;
        }
        if(answer3 == 3){
            correctCount++;
        }

        System.out.println("You got " + correctCount + " questions correct!");

        if(correctCount == 0){
            System.out.println("Wow, you need to brush up on your trivia.");
        } else if (correctCount == 3){
            System.out.println("Wow, you're great at trivia!");
        }
    }
}
