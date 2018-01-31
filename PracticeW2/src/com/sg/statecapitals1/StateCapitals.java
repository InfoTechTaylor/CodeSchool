package com.sg.statecapitals1;

import java.util.HashMap;
import java.util.Set;

public class StateCapitals {
    public static void main(String[] args) {
        // create a HashMap to hold the names of all the state capitals
        // state name will be key and capital name is the value
        HashMap<String, String> stateCapitals = new HashMap<>();

        // load the HashMap with each state/capital pair
        stateCapitals.put("Alabama", "Montgomery");
        stateCapitals.put("Alaska", "Juneau");
        stateCapitals.put("Arizona", "Phoenix");
        stateCapitals.put("Arkansas", "Little Rock");

        // print all of the state names to the screen
        Set<String> keys = stateCapitals.keySet();

        System.out.println("STATES: ");
        System.out.println("=======");
        for(String k: keys){
            System.out.println(k);
        }

        // print all of the capital names to the screen
        System.out.println("\nCAPITALS:");
        System.out.println("==========");
        for(String k: keys){
            System.out.println(stateCapitals.get(k));
        }

        // print each state along with its capital to the screen
        System.out.println("\nSTATE/CAPITAL PAIRS: ");
        System.out.println("============================");
        for(String k: keys){
            System.out.println(k + " - " + stateCapitals.get(k));
        }
    }
}
