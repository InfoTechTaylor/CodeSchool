package com.tsguild.foundations.com.tsguild.foundations.random;

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {
        Random randomizer = new Random();
        String[] fortunes = {"Those aren't the droids you're looking for.",
                            "Never go in against a Sicilian when  death is on the line!",
                            "Goonies never say die.",
                            "With great power there must also come - great responsibility.",
                            "Never argue with the data.",
                            "Try not. Do, or do not. There is not try.",
                            "You are a leaf on the wind, watch how you soar.",
                            "Do absolutely nothing, and it will be everything that you thought it could be.",
                            "Kneel before Zod.",
                            "Make it so."};

        int randomNumber = randomizer.nextInt(10);
        String fortune = fortunes[randomNumber];

        System.out.println(fortune);
    }
}
