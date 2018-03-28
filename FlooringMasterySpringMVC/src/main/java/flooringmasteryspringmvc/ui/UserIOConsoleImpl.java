package flooringmasteryspringmvc.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        double number = 0d;
        boolean invalidInput;

        do {
            try {
                // display prompt
                print(prompt);
                // read in user input as a String
                String numberString = inputReader.nextLine();
                // convert user input to a Double
                number = Double.parseDouble(numberString);
                invalidInput = false;
            } catch (NumberFormatException e) { // if the string does not contain a parsable double
                print("Please enter a valid number of type double.");
                invalidInput = true;
            }
        } while(invalidInput);
        // return a double
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean invalidInput;
        double number = 0d;
        do{
            try {
                // display prompt
                print(prompt);

                // read in double from user as a String
                String numberString = inputReader.nextLine();

                // convert user number to a Double
                number = Double.parseDouble(numberString);

                invalidInput = false;

                // validate double is between min and max
                if (number < min || number > max) {
                    invalidInput = true;
                    print("Invalid number. Please enter number between " + min + " and " + max);
                }

            } catch (NumberFormatException e) { // if the string does not contain a parsable double
                print("Please enter a valid number of type double.");
                invalidInput = true;
            }

        }while(invalidInput);

        // return the double
        return number;
    }

    @Override
    public float readFloat(String prompt) {
        float number = 0f;
        boolean invalidInput;
        do {
            try {
                // display prompt
                print(prompt);
                // read in user input as a String
                String numberString = inputReader.nextLine();
                // convert user input to a float
                number = Float.parseFloat(numberString);
                invalidInput = false;
            } catch (NumberFormatException e) { // if the string does not contain a parsable float
                print("Please enter a valid number of type double.");
                invalidInput = true;
            }
        } while(invalidInput);
        // return a float
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean invalidInput;
        float number = 0f;
        do{
            try {
                // display prompt
                print(prompt);

                // read in float from user as a String
                String numberString = inputReader.nextLine();

                // convert user number to a float
                number = Float.parseFloat(numberString);

                invalidInput = false;

                // validate float is between min and max
                if (number < min || number > max) {
                    invalidInput = true;
                    print("Invalid number. Please enter number between " + min + " and " + max);
                }

            } catch (NumberFormatException e) { // if the string does not contain a parsable float
                print("Please enter a valid number of type double.");
                invalidInput = true;
            }

        }while(invalidInput);

        // return the float
        return number;
    }

    @Override
    public int readInt(String prompt) {
        int number = 0;
        boolean invalidInput;
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
        boolean invalidInput;
        int number = 0;
        do{
            try {
                // display prompt
                print(prompt);

                // read in int from user as a String
                String numberString = inputReader.nextLine();

                // convert user number to a int
                number = Integer.parseInt(numberString);
                invalidInput = false;

                // validate int is between min and max
                if (number < min || number > max) {
                    invalidInput = true;
                    print("Invalid number. Please enter number between " + min + " and " + max);
                }

            } catch (NumberFormatException e) { // e is most often e or ex in code
                print("Please enter a valid integer");
                invalidInput = true;
            }

        }while(invalidInput);

        // return the int
        return number;
    }

    @Override
    public long readLong(String prompt) {
        Long number = 0L;
        boolean invalidInput;
        do {
            try {
                // display prompt
                print(prompt);
                // read in user input as a String
                String numberString = inputReader.nextLine();
                // convert user input to a long
                number = Long.parseLong(numberString);
                invalidInput = false;
            } catch (NumberFormatException e) { // if the string does not contain a parsable long
                print("Please enter a valid integer");
                invalidInput = true;
            }
        } while(invalidInput);
        // return a long
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean invalidInput;
        long number = 0L;
        do{
            try {
                // display prompt
                print(prompt);

                // read in long from user as a String
                String numberString = inputReader.nextLine();

                // convert user number to a long
                number = Long.parseLong(numberString);
                invalidInput = false;

                // validate long is between min and max
                if (number < min || number > max) {
                    invalidInput = true;
                    print("Invalid number. Please enter number between " + min + " and " + max);
                }

            } catch (NumberFormatException e) { // if the string does not contain a parsable long
                print("Please enter a valid integer");
                invalidInput = true;
            }

        }while(invalidInput);

        // return the long
        return number;
    }

    @Override
    public String readString(String prompt) {
        // display prompt
        print(prompt);
        // read input into a string and return
        return inputReader.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean invalidInput = true;
        BigDecimal number = new BigDecimal("0");

        while(invalidInput) {
            try {
                //display prompt
                print(prompt);

                // read in string from user as a BigDecimal
                number = new BigDecimal(inputReader.nextLine());
                invalidInput = false;
            } catch (NumberFormatException e) {
                print("Please enter a valid number");
                invalidInput = true;
            }
        }
        return number;
    }

    @Override
    public BigDecimal readBigDecimalAllowNull(String prompt) {
        boolean invalidInput = true;
        BigDecimal number = new BigDecimal("0");

        while(invalidInput) {
            try {
                //display prompt
                print(prompt);

                String nextLine = inputReader.nextLine();

                // read in string from user as a BigDecimal
                if(nextLine.equals("")) {
                    number = null;
                } else {
                    number = new BigDecimal(nextLine);
                }
                invalidInput = false;
            } catch (NumberFormatException e) {
                print("Please enter a valid number");
                invalidInput = true;
            }
        }
        return number;
    }


    @Override
    public LocalDate readLocalDate(String prompt){
        LocalDate date = null;
        boolean isInvalid = true;
        while(isInvalid) {
            try {
                print(prompt);
                String input = inputReader.nextLine();
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                isInvalid = false;
            } catch (DateTimeParseException e) {
                print("Please enter a valid date in MM/dd/yyyy format.");
                isInvalid = true;
            }
        }
        return date;

    }

    @Override
    public LocalDate readLocalDateAllowNull(String prompt){
        LocalDate date = null;
        boolean isInvalid = true;
        while(isInvalid) {
            try {
                print(prompt);
                String input = inputReader.nextLine();


                // read in string from user as a BigDecimal
                if(input.equals("")) {
                    date = null;
                } else {
                    date = LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                }

                isInvalid = false;
            } catch (DateTimeParseException e) {
                print("Please enter a valid date in MM/dd/yyyy format.");
                isInvalid = true;
            }
        }
        return date;

    }


}
