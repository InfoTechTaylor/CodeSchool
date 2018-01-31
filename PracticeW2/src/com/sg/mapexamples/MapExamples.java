package com.sg.mapexamples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExamples {

    public static void main(String[] args) {
        Map<String, Integer> populations = new HashMap<>();

        populations.put("USA", 200000000);
        populations.put("Canada", 34000000);
        populations.put("United Kingdom", 63000000);
        populations.put("Japan", 127000000);

        System.out.println(populations.size());

        Integer usaPopulation = populations.get("USA");

        System.out.println(usaPopulation);

        populations.put("USA", 313000000);

        usaPopulation = populations.get("USA");
        System.out.println(usaPopulation);

        Set<String> keys = populations.keySet();

        for(String currentKey : keys){
            Integer currentPopulation = populations.get(currentKey);
            System.out.println("The population of " + currentKey + " is " + currentPopulation);
        }

        Collection<Integer> populationValues = populations.values();

        for(Integer currentPopulation : populationValues) {
            System.out.println(currentPopulation);
        }
    }
}
