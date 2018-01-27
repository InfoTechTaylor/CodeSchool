package com.tsguild.foundations.com.tsguild.foundations.com.tsguild.foundations.basics.arrays;

public class FruitSalad {
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};

        String[] fruitSalad;
        fruitSalad = new String[12];

        int appleCount = 0;
        int orangeCount = 0;
        int berryCount = 0;
        for(int i = 0; i < fruitSalad.length; i++){
            for(int k=0; k < fruit.length; k++) {
                if(fruitSalad[i] == null && fruit[k] != null) {
                    if (fruit[k].endsWith("berry")) {
                        fruitSalad[i] = fruit[k];
                        fruit[k] = null;
                        berryCount++;
                    } else if (fruit[k].endsWith("Apple")){
                        if (appleCount <3){
                            fruitSalad[i] = fruit[k];
                            fruit[k] = null;
                            appleCount++;
                        }
                    } else if (fruit[k].endsWith("Orange")){
                        if (orangeCount < 2){
                            fruitSalad[i] = fruit[k];
                            fruit[k] = null;
                            orangeCount++;
                        }
                    } else if (!fruit[k].endsWith("Tomato")){
                        fruitSalad[i] = fruit[k];
                        fruit[k] = null;

                    }
                }
            }
        }

        System.out.println("Hooray we have a fruit salad! There is: ");
        System.out.println(appleCount + " apples");
        System.out.println(orangeCount + " oranges");
        System.out.println(berryCount + " berries");
        System.out.println("in the salad! ");

    } // end main
} // end class
