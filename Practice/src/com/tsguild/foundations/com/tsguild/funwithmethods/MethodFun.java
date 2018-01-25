package com.tsguild.foundations.com.tsguild.funwithmethods;

public class MethodFun {
    public static void main(String[] args) {
//        doIT();
//        doIT();
//        doIT();
        int num = get5();
        System.out.println(num);
        System.out.println(get5());

        silly(42);
        int num2 = 44;
        silly(num2);
        silly(5+4);

        int operand1 = 3;
        int operand2 = 7;
        int sum = add(operand1, operand2);
        System.out.println(sum);

        sum = add(32, 45);
        System.out.println(sum);

        System.out.println(add(3,4));


    } // end main

    public static int add(int a, int b){
        return a + b;
    }

    public static void silly(int i){
        System.out.println("My parameter is: " + i);
    }

    public static int get5(){
        return 5;
    }

    public static void doIT(){
        System.out.println("Hello");
    }
} // end class
