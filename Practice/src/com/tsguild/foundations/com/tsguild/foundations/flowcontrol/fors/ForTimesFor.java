package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

public class ForTimesFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userNumString, usersAnswerString;
        int userNum=0, usersAnswer=0, answer=0;

        System.out.println("Which times table shall I recite? ");
        userNumString = scanner.nextLine();
        userNum = Integer.parseInt(userNumString);

        int countCorrect = 0;
        for(int i=1; i <= 15; i++){
            System.out.println(i + " * " + userNum + " is: " );
            usersAnswerString = scanner.nextLine();
            usersAnswer = Integer.parseInt(usersAnswerString);
            answer=i*userNum;


            if(usersAnswer == answer){
                System.out.println("Correct!");
                countCorrect++;
            } else{
                System.out.println("Sorry no, the answer is: " + answer);
            }
        }

        System.out.println("You got " + countCorrect + " correct.");
        double totalScore = countCorrect / 15.0;
        if(totalScore<= .5){
            System.out.println("You should study more!");
        } else if (totalScore >= .9){
            System.out.println("Congratulations! ");
        }
    }
}
