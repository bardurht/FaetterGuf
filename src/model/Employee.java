package model;

public class Employee extends Person {

    //Constructor
    //Uses getters and setters from Person (superClass)
    public Employee(String firstName, String lastName, String emailAddress, String phone) {
        super(firstName, lastName, emailAddress, phone);
    }

}
