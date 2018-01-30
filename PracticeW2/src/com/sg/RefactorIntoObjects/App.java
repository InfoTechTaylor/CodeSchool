package com.sg.RefactorIntoObjects;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Factorizor
        Factorizor factorizor = new Factorizor(); // constructor
        factorizor.runFactorizor();

        

    }
}
