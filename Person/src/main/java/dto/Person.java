package dto;

import java.util.function.Predicate;

public class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // method to tell us if Person is old enough to vote
    public boolean test(Person person) {
        return person.getAge() >= 18;
    }


    // -> between the parameter list and the open curly brace is a lambda specific syntax and required
    // when defining lambda
    Predicate<Person> oldEnoughToVote = (Person p) -> {
      return p.getAge() >= 18;
    };


    Predicate<Person> oldEnoughToVoteV2 = p -> p.getAge() >= 18;
}