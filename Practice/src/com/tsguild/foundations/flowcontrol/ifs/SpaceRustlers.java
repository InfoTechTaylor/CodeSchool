package com.tsguild.foundations.flowcontrol.ifs;

public class SpaceRustlers {
    public static void main(String[] args) {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;

        if(aliens > spaceships) {
            System.out.println("Vrroom, vroom! Let's get going!");
        } else {
            System.out.println("There aren't enough green guys to drive these ships!");
        }

        if(cows == spaceships) {
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");
        } else if (cows > spaceships) {
            System.out.println("Dangit! I don't how we're going to fit all these cows in here!");
        } else {
            System.out.println("Too many ships! Not enough cows.");
        }

        if(aliens > cows){
            System.out.println("Hurrah, we've got the grub! Hamburger party on Alpha Centauri!");
        } else if (cows >= aliens){
            System.out.println("Oh no! The herds got restless and took over! Looks like _we're_ hamburger now!!");
        }
    }
}

// the else if and if adds blocks of potentially executable code given that the condition specified is met,
// once a condition is met in a given if, else if, else block, it will exit the block of ifs and continue

// if we remove the else from the if, then if no conditions are met, nothing will happen, nothing
// will get displayed to the screen
