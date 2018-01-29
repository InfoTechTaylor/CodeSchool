package com.tsguild.foundations.com.tsguild.foundations.flowcontrol.fors;

public class ForByFor {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            System.out.print("|");

            for (int j = 0; j < 3; j++) {

                if(i == 0 || i ==2){
                    if(j == 1){
                        for (int k = 0; k < 3; k++) {
                            System.out.print("$");
                        }
                    } else {
                        for (int k = 0; k < 3; k++) {
                            System.out.print("*");
                        }
                    }
                } else if (i == 1){
                    if(j==1){
                        for (int k = 0; k < 3; k++) {
                            System.out.print("#");
                        }
                    } else {
                        for (int k = 0; k < 3; k++) {
                            System.out.print("@");
                        }
                    }
                }


                System.out.print("|");
            }
            System.out.println("");
        }
    }
}
