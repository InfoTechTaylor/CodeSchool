package com.sg.listexamples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExamples {
    public static void main(String[] args) {

//        List<String> stringList = new ArrayList<>(); // empty list of strings
//
//        System.out.println("List size: " + stringList.size());
//
//        stringList.add("The first string.");
//
//        System.out.println("List size: " + stringList.size());
//
//        stringList.add("The second string.");
//
//        System.out.println("List size: " + stringList.size());
//
//        stringList.remove(1);
//
//        System.out.println("List size: " + stringList.size());
//
//        stringList.remove(0);
//
//        System.out.println("List size: " + stringList.size());

        // create an ArrayList of String objects
        List<String> stringList = new ArrayList<>();

        // add a String object ot our list
        stringList.add("My First String");

        // add another String object to our list
        stringList.add("My Second String");

        stringList.add("My third String");

        stringList.add("My fourth String list");

        // ask the list how bit it is
        System.out.println("List Size: " + stringList.size());

        // ehanced for loop
        for(String currentString: stringList){ // for each string, currentString in stringList, go ahead and pull that out and operate in the for loop
            System.out.println(currentString);
        }

        // iterator
        Iterator<String> iterator = stringList.iterator();

        while(iterator.hasNext()) {
            String currentString = iterator.next();
            System.out.println(currentString);
        }
    }
}
