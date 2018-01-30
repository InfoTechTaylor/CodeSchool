package com.sg.simplecalculator;

import sun.java2d.pipe.SpanShapeRenderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isChoiceExit = false;
        int userChoice;
        double operand1, operand2, result=0;

        do {
            System.out.println("Select a calculator option: ");
            System.out.println("1. Add numbers ");
            System.out.println("2. Subtract numbers ");
            System.out.println("3. Divide numbers ");
            System.out.println("4. Multiple numbers ");
            System.out.println("5. Exit \n");

            // get user's input
            userChoice = Integer.parseInt(sc.nextLine());

            if(userChoice == 5){
                isChoiceExit = true;
            } else {
                System.out.println("Enter a number: ");
                operand1 = Double.parseDouble(sc.nextLine());
                System.out.println("Enter the second number: ");
                operand2 = Double.parseDouble(sc.nextLine());

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

            System.out.println("Result: " + result + "\n");

            } // end else
        } while(isChoiceExit==false);

    }
}
