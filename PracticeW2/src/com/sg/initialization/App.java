package com.sg.initialization;

public class App {
    public static void main(String[] args) {
//        int count = 0;
//        Person person = new Person();
//        person.setAge(25);
//        person.setName("Taylor");
//
//        System.out.println("Count = " + count);
//        System.out.println("Age = " + person.getAge());
//        System.out.println("Person = " + person.getName());

        int count = 19;
        changeMyValue(count);
        System.out.println("Value of count = " + count);

        Person myPerson = new Person();
        myPerson.setAge(25);
        myPerson.setName("Taylor");
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());

        changeMyName(myPerson);
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());
    }

    public static void changeMyValue(int value){
        value = 21;
    }
    public static void changeMyName(Person person){
        person.setName("Brett");
    }
}
