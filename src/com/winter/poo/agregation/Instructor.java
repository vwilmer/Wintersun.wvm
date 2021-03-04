package com.winter.poo.agregation;

public class Instructor {

    private String lastName;
    private String firstName;
    private String officeNumber;

    public Instructor(String lastName, String firstName, String officeNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.officeNumber = officeNumber;
    }

    public Instructor(Instructor i) {
        this.lastName = i.lastName;
        this.firstName = i.firstName;
        this.officeNumber = i.officeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    @Override
    public String toString() {
        return "Instructor [lastName=" + lastName + ", firstName=" + firstName + ", officeNumber=" + officeNumber + "]";
    }

}
