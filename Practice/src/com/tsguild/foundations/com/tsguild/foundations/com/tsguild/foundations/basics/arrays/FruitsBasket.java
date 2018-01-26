package com.tsguild.foundations.com.tsguild.foundations.com.tsguild.foundations.basics.arrays;

public class FruitsBasket {
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        int orangeCount=0, appleCount=0;

        for(int i=0; i < fruit.length; i++){
            if(fruit[i].equals("Orange")){
                orangeCount++;
            } else if(fruit[i].equals("Apple")){
                appleCount++;
            }
        }

        String[] apples = new String[appleCount];
        String[] oranges = new String[orangeCount];

        for(int i=0; i < fruit.length; i++){
            if(fruit[i].equals("Orange")){

                for(int f=0; f < oranges.length; f++){
                    oranges[f] = fruit[i];
                }

            } else if(fruit[i].equals("Apple")){
                for(int f=0; f < apples.length; f++){
                    apples[f] = fruit[i];
                }
            }
        }

        System.out.println("Total Fruit Count: " + (orangeCount + appleCount));
        System.out.println("Total Oranges: " + oranges.length);
        System.out.println("Total APples: " + apples.length);

    }
}
