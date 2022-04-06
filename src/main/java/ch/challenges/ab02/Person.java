package ch.challenges.ab02;

import lombok.Data;

@Data
public class Person {

    private String name;
    private String firstName;

    public Person(String firstName, String name) {
        this.firstName = firstName;
        this.name = name;
    }
}
