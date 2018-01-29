package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

public class TheCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String startNumString, endNumString, countByString;
        int startNum, endNum, countBy;

        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.println("Start at : ");
        startNumString = scanner.nextLine();
        startNum = Integer.parseInt(startNumString);
        System.out.println(" Stop at : ");
        endNumString = scanner.nextLine();
        endNum = Integer.parseInt(endNumString);
        System.out.println("Count by : ");
        countByString = scanner.nextLine();
        countBy = Integer.parseInt(countByString);

        int count = 0;
        for (int i = startNum; i <= endNum; i+=countBy){
            System.out.print(i + " ");
            count++;

            if(count == 3){
                System.out.print("- Ah ah ah!\n");
                count=0;
            }
        }
    }
}
