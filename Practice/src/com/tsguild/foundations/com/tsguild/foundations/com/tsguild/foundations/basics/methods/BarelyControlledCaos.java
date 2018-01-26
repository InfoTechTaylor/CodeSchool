package com.tsguild.foundations.com.tsguild.foundations.com.tsguild.foundations.basics.methods;

import java.util.Random;

public class BarelyControlledCaos {

    static Random randomizer = new Random();

    public static void main(String[] args) {

        String color = getRandomColor();
        String animal = getRandomAnimal();
        String colorAgain = getRandomColor();
        int weight = getRandomInt(200, 5);
        int distance = getRandomInt(20, 10);
        int number = getRandomInt(20000, 10000);
        int time = getRandomInt(6, 2);

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
                    + weight + "lb miniature " + animal
                    + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
                    + number + " " + colorAgain + " poppies for nearly "
                    + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
                    + "let me tell you!");


    }

    public static String getRandomColor(){
        int colorPicker = randomizer.nextInt(5);

        String[] colors = {"red", "orange", "yellow", "green", "blue"};

        String color = colors[colorPicker];

        return color;

    }

    public static String getRandomAnimal(){
        int animalPicker = randomizer.nextInt(5);

        String[] animals = {"dog", "cat", "monkey", "owl", "elephant"};

        String animal = animals[animalPicker];

        return animal;
    }

    public static int getRandomInt(int max, int min){
        max = (max - min) + 1;
        int randomInt = (randomizer.nextInt(max) + min);
        return randomInt;
    }
}
