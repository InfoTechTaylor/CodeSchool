package com.sg.simplecalculator;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    // impl is coming from an interface
    // console is the type of implementation for the interface

    private Scanner inputReader = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        // display prompt
        print(prompt);
        // read in user input as a String
        String numberString = inputReader.nextLine();
        // convert user input to a Double
        double number = Double.parseDouble(numberString);
        // return a double
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean invalidInput = false;
        double number = 0;
        do{
            // display prompt
            print(prompt);

            // read in double from user as a String
            String numberString = inputReader.nextLine();

            // convert user number to a Double
            number = Double.parseDouble(numberString);

            // validate double is between min and max
            if(number < min || number > max){
                invalidInput = true;
                print("Invalid number. Please enter number between " + min + " and " + max);
            }

        }while(invalidInput);

        // return the double
        return number;
    }

    @Override
    public float readFloat(String prompt) {
        // display prompt
        print(prompt);
        // read in user input as a String
        String numberString = inputReader.nextLine();
        // convert user input to a float
        float number = Float.parseFloat(numberString);
        // return a float
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean invalidInput = false;
        float number = 0;
        do{
            // display prompt
            print(prompt);

            // read in float from user as a String
            String numberString = inputReader.nextLine();

            // convert user number to a float
            number = Float.parseFloat(numberString);

            // validate float is between min and max
            if(number < min || number > max){
                invalidInput = true;
                print("Invalid number. Please enter number between " + min + " and " + max);
            }

        }while(invalidInput);

        // return the float
        return number;
    }

    @Override
    public int readInt(String prompt) {
        int number = 0;
        boolean invalidInput = false;
        do {
            try {
                // display the prompt using existing method of this class to print
                print(prompt);
                // read in the user input as a String
                String numberString = inputReader.nextLine();
                // convert the String to an int
                number = Integer.parseInt(numberString);
                invalidInput = false;
            } catch (NumberFormatException e) { // e is most often e or ex in code
                print("Please enter a valid integer");
                invalidInput = true;
            }
        } while(invalidInput);

        // return that int
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean invalidInput = false;
        int number = 0;
        do{
            // display prompt
            print(prompt);

            // read in int from user as a String
            String numberString = inputReader.nextLine();

            // convert user number to a int
            number = Integer.parseInt(numberString);

            // validate int is between min and max
            if(number < min || number > max){
                invalidInput = true;
                print("Invalid number. Please enter number between " + min + " and " + max);
            }

        }while(invalidInput);

        // return the int
        return number;
    }

    @Override
    public long readLong(String prompt) {
        // display prompt
        print(prompt);
        // read in user input as a String
        String numberString = inputReader.nextLine();
        // convert user input to a long
        long number = Long.parseLong(numberString);
        // return a long
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean invalidInput = false;
        long number = 0;
        do{
            // display prompt
            print(prompt);

            // read in long from user as a String
            String numberString = inputReader.nextLine();

            // convert user number to a long
            number = Long.parseLong(numberString);

            // validate long is between min and max
            if(number < min || number > max){
                invalidInput = true;
                print("Invalid number. Please enter number between " + min + " and " + max);
            }

        }while(invalidInput);

        // return the long
        return number;
    }

    @Override
    public String readString(String prompt) {
        // display prompt
        print(prompt);
        // read in user input as a String
        String string = inputReader.nextLine();

        // return a String
        return string;
    }


}
