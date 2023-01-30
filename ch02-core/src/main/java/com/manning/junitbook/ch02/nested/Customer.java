package com.manning.junitbook.ch02.nested;

import java.util.Date;
import java.util.Objects;

/**
 * An **inner class** is a class that is a member of another class.
 * It can access any private instance variable of the outer class, as it is effectively part of that outer class.
 */
public class Customer {
    private Gender gender;
    private String firstName;
    private String lastName;

    private String middleName;
    private Date becomeCustomer;

    public static class Builder{
        private Gender gender;
        private String lastName;
        private String firstName;

        private String middleName;
        private Date becomeCustomer;

        public Builder(Gender gender, String firstName, String lastName) {
            this.gender = gender;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder withBecomeCustomer(Date becomeCustomer) {
            this.becomeCustomer = becomeCustomer;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    private Customer(Builder builder) {
        this.gender = builder.gender;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.becomeCustomer = builder.becomeCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBecomeCustomer() {
        return becomeCustomer;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return gender == customer.gender &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(middleName, customer.middleName) &&
                Objects.equals(becomeCustomer, customer.becomeCustomer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gender, firstName, lastName, middleName, becomeCustomer);
    }
}
