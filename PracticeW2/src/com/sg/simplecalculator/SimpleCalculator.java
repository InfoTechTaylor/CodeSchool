package com.sg.simplecalculator;

public class SimpleCalculator {
    private static double result;

    public static double addOperands(double operand1, double operand2){
        result = operand1 + operand2;
        return result;
    }

    public static double subtractOperands(double operand1, double operand2){
        result = operand1 - operand2;
        return result;
    }

    public static double divideOperands(double operand1, double operand2){
        result = operand1/operand2;
        return result;
    }

    public static double multiplyOperands(double operand1, double operand2){
        result = operand1 * operand2;
        return result;
    }
}
