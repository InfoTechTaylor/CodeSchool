package com.sg.simplecalculator;

import sun.java2d.pipe.SpanShapeRenderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        UserIOConsoleImpl appIO = new UserIOConsoleImpl(); // instantiate userIO class object;

        boolean isChoiceExit = false;
        int userChoice;
        double operand1, operand2, result=0;

        do {
            // get user's input
            userChoice = appIO.readInt("Select a calculator option: \n" +
                                        "1. Add numbers \n" +
                                        "2. Subtract numbers \n" +
                                        "3. Divide numbers \n" +
                                        "4. Multiple numbers \n" +
                                        "5. Exit \n", 1, 5);

            if(userChoice == 5){
                isChoiceExit = true;
            } else {
                operand1 = appIO.readDouble("Enter a number: ");
                operand2 = appIO.readDouble("Enter the second number: ");


            // call appropriate SimpleCalculator method depending on user's choice
            switch(userChoice) {
                case 1:
                    result = SimpleCalculator.addOperands(operand1, operand2);
                    break;
                case 2:
                    result = SimpleCalculator.subtractOperands(operand1, operand2);
                    break;
                case 3:
                    result = SimpleCalculator.divideOperands(operand1, operand2);
                    break;
                case 4:
                    result = SimpleCalculator.multiplyOperands(operand1, operand2);
                    break;
            } // end switch

            appIO.print("Result: " + result + "\n");

            } // end else
        } while(isChoiceExit==false);

    }
}
