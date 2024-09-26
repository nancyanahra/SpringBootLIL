package com.nancynahra.lil.learning_spring.data;

import jakarta.persistence.*;

@Entity
@Table(name="GUEST")
public class Guest {



    @Id
    //default strategy, but prefer to go ahead and specify it
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="GUEST_ID")
    private long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="STATE")
    private String state;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public long getGuestId() {
        return id;
    }
}
