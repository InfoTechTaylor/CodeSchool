package com.tsguild.foundations.com.tsguild.com.tsguild.arrays;

public class Arrays {
    public static void main(String[] args) {

        int[] teamScores; //declare an array

        //array initialization requires a new array object
        teamScores = new int[5];

        teamScores[0] = 2;
        teamScores[1] = 45;
        teamScores[2] = 4;
        teamScores[3] = 8;
        teamScores[4] = 99;

        int[] golfScores = {72, 74, 68, 71};

        int currentGolfScore = golfScores[0];

        golfScores[2] = 70;

        for(int i=0; i < golfScores.length; i++){
            System.out.println(golfScores[i]);
        }

        // enhanced for loop for arrays 
        for(int currentScore : golfScores){ // for each integer, currentScore, that is in golfScores, and put the next one in current score
            System.out.println(currentScore);
        }

    }
}
