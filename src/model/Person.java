package model;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    //Fields
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty emailAddress;
    private SimpleStringProperty phone;

    //Constructor
    public Person(String firstName, String lastName, String emailAddress, String phone) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.phone = new SimpleStringProperty(phone);
    }

    //Getters & Setters

    //First Name
    public String getFirstName() {
        return firstName.get();
    }
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    //Last Name
    public String getLastName() {
        return lastName.get();
    }
    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    //Email
    public String getEmailAddress() {
        return emailAddress.get();
    }
    public SimpleStringProperty emailAddressProperty() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    //Phone
    public String getPhone() {
        return phone.get();
    }
    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

}