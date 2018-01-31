package com.sg.objectdemo;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Person(){
        //default constructor
        // this is called overloading, when you have two methods with the same name but the parameter list is different
        // so they are different methods
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {  //accessor
        return name;
    }

    public void setName(String name) { // mutators
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
