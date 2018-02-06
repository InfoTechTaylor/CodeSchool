package EnumExercise.MathOperators;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first operand (number): ");
        String operandString1 = sc.nextLine();
        int operand1 = Integer.parseInt(operandString1);
        System.out.println("Enter the second operand (number): ");
        String operandString2 = sc.nextLine();
        int operand2 = Integer.parseInt(operandString2);

        IntMath math = new IntMath();

        for(MathOperator operator : MathOperator.values()){
            System.out.println(operator + ": " + math.calculate(operator, operand1, operand2));
        }

    }
}

