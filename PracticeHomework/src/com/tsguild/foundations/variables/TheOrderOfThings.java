package com.tsguild.foundations.variables;

public class TheOrderOfThings {
    public static void main(String[] args) {

        double number;
        String opinion, size, age, shape, color, origin, material, purpose;
        String noun;

        number = 5.0;
        opinion = "AWESOME";
        size = "teensy-weensy";
        age = "new";
        shape = "oblong";
        color = "BRIGHT yellow";
        origin = "AlphaCentaurian";
        material = "plantinum";
        purpose = "good";

        noun = "dragons";

        // Using the + with strings, doesn't add it concatenantes! (Sticks them together)
        System.out.println( number + " " + opinion + " " + size + " " + age + " " + shape + " "
                + color + " " + origin + " " + material + " " + purpose + " " + noun + ".");
    }
}
