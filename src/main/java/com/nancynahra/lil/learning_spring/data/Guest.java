package com.nancynahra.lil.learning_spring.data;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="GUEST")
public class Guest {





    @Id
    //default strategy, but prefer to go ahead and specify it
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Reservation> reservations;

    public Guest() {
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.emailAddress = null;
        this.address = null;
        this.country = null;
        this.state = null;
        this.phoneNumber = null;
    }


        public Guest(String firstName, String lastName, String emailAddress, String address, String country, String state, String phoneNumber) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

//    public Guest getGuestByLastNameAndFirstName(String lastName, String firstName) {
//
//
//    }

    public void setReservations(Set<Reservation> reservations) {

        this.reservations = reservations;
    }

    public Set<Reservation> getReservations(){
        return reservations;
    }

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


    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
