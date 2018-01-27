package com.tsguild.foundations.com.tsguild.foundations.com.tsguild.foundations.basics.arrays;

public class SimpleSort {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];

        // combine arrays into one
        int wholeNumbersIndex = 0;
        for (int i = 0; i < firstHalf.length; i++) {
            if (wholeNumbers[wholeNumbersIndex] == 0) {
                wholeNumbers[wholeNumbersIndex] = firstHalf[i];
                wholeNumbersIndex++;
            }
        }
        for (int i = 0; i < secondHalf.length; i++) {
            if (wholeNumbers[wholeNumbersIndex] == 0) {
                wholeNumbers[wholeNumbersIndex] = secondHalf[i];
                wholeNumbersIndex++;
            }
        }


        // Sorting code should go here!
        int temp;
        for (int i = 1; i < wholeNumbers.length; i++) {

            for (int j = 0; j < wholeNumbers.length - 1; j++) {
                if (wholeNumbers[j] > wholeNumbers[j + 1]) {
                    temp = wholeNumbers[j];
                    wholeNumbers[j] = wholeNumbers[j + 1];
                    wholeNumbers[j + 1] = temp;
                }

            }
        }

        System.out.println("Here ya go - all nice and ordered: " );
        for(int i=0; i < wholeNumbers.length; i++){
            System.out.print(wholeNumbers[i] + " ");
        }
    }
}
