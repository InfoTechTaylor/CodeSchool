package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class BirthStones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int birthMonth;

        System.out.println("What month's birthstone are you wanting to know? ");
        birthMonth = scanner.nextInt();

        if(birthMonth == 1){
            System.out.println("January's birthstone is Garnet.");
        } else if(birthMonth == 2){
            System.out.println("February's birthstone is Amethyst.");
        } else if(birthMonth == 3){
            System.out.println("March's birthstone is Aquamarine.");
        } else if(birthMonth == 4){
            System.out.println("April's birthstone is Diamond.");
        } else if(birthMonth == 5){
            System.out.println("May's birthstone is Emerald.");
        } else if(birthMonth == 6){
            System.out.println("June's birthstone is Pearl.");
        } else if(birthMonth == 7){
            System.out.println("July's birthstone is Ruby.");
        } else if(birthMonth == 8){
            System.out.println("August's birthstone is Peridot.");
        } else if(birthMonth == 9){
            System.out.println("September's birthstone is Sapphire.");
        } else if(birthMonth == 10) {
            System.out.println("October's birthstone is Opal.");
        } else if(birthMonth == 11){
            System.out.println("November's birthstone is Topaz.");
        } else if(birthMonth == 12){
            System.out.println("December's birthstone is Turquoise.");
        } else {
            System.out.println("I think you must be confused, " + birthMonth + " doesn't match a month.");
        }

    }
}
