package com.sg.statecapitals2;

import java.util.HashMap;
import java.util.Set;

public class StateCapitals2 {
    public static void main(String[] args) {
        UserIOConsoleImpl appIO = new UserIOConsoleImpl();
        // create HashMap to hold state as String and value as Capital object
        HashMap<String, Capital> stateCapitals= new HashMap<>();

        // create capital objects
        Capital montgomeryObj = new Capital("Montgomery", 205000,
                            156);
        Capital juneauObj = new Capital("Juneau", 31000,
                            3255);
        Capital phoenixObj = new Capital("Phoenix", 1445000,
                            517);
        Capital littleRockObj = new Capital("Little Rock", 193000,
                            116);

        // add states and capital objects to the HashMap
        stateCapitals.put("Alabama", montgomeryObj);
        stateCapitals.put("Alaska", juneauObj);
        stateCapitals.put("Arizona", phoenixObj);
        stateCapitals.put("Arkansas", littleRockObj);

        // print the name, population and square mileage for each capital
        // along with its State name
        appIO.print("STATE/CAPITAL PAIRS: ");
        appIO.print("=======================");
        Set<String> keys = stateCapitals.keySet();
        for(String k: keys){
            appIO.print(k + " - " + stateCapitals.get(k).getName() +
                    " | Pop: " + stateCapitals.get(k).getPopulation() +
                    " | Area: " + stateCapitals.get(k).getSquareMileage() +
                    " sq mi");
        }

        // print the states with capitals that have a population over
        // a given value provided by the user

        int userProvidedPopulation = appIO.readInt("Please enter the" +
                            " lower limit for capital city population: ");

        appIO.print("LIST CAPITALS WITH POPULATIONS GREATER THAN " +
                            userProvidedPopulation);

        for(String k: keys){
            if(stateCapitals.get(k).getPopulation() >= userProvidedPopulation) {
                appIO.print(k + " - " + stateCapitals.get(k).getName() +
                        " | Pop: " + stateCapitals.get(k).getPopulation() +
                        " | Area: " + stateCapitals.get(k).getSquareMileage() +
                        " sq mi");
            }
        }


    }
}
