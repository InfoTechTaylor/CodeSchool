package com.sg.objectdemo;

import java.util.Scanner;

public class VotingSystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int age = 0;
        boolean oldEnoughToVote;

        Person taylor = new Person("Taylor", 25); // new keyword creates an instance of a class
//        taylor.setName("Taylor");
//        taylor.setAge(25);

        Person eric = new Person("Eric", 12);
//        eric.setName("Eric");
//        eric.setAge(12);

//        for (int i = 0; i < 2; i++){
//            age = promptUserForAge();

            oldEnoughToVote = isOldEnoughToVote(taylor.getAge());
            boolean oldEnoughToVote2 = isOldEnoughToVote(eric.getAge());

            printOldEnoughToVote(oldEnoughToVote);
            printOldEnoughToVote(oldEnoughToVote2);
//        }
    }

    public static int promptUserForAge(){
        int age;
        String ageString;

        System.out.println("What is your age? ");
        ageString = scanner.nextLine();
        age = Integer.parseInt(ageString);

        return age;
    }

    public static boolean isOldEnoughToVote(int age){
        if(age >= 18){
            return true;
        } else{
            return false;
        }

    }

    public static void printOldEnoughToVote(boolean oldEnoughToVote){
        if(oldEnoughToVote){
            System.out.println("You're old enough to vote");
        } else {
            System.out.println("You are not old enough to vote");
        }
    }


}
