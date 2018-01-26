package com.tsguild.foundations.com.tsguild.foundations.com.tsguild.foundations.basics.methods;

import java.util.Scanner;

public class VoteExample {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {



        for(int i=0; i <2; i++){

            int age = getUserAge();
            boolean oldEnoughToVote = isOldEnoughToVote(age);

            if(oldEnoughToVote){
                System.out.println("You are old enough to vote!");
            } else {
                System.out.println("You are not old enough to vote, sorry. ");
            }
        }



    }

    public static int getUserAge(){

        int userAge;
        boolean oldEnoughToVote;

        System.out.println("How old are you? ");
        String userAgeString = scanner.nextLine();
        userAge = Integer.parseInt(userAgeString);

        return userAge;
    }

    public static boolean isOldEnoughToVote(int age){
        if (age >= 18 ){
            return true;
        } else {
            return false;
        }
    }

}
