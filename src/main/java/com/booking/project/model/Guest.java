package com.booking.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guests")
public class Guest extends Person {
    private int numberOfCancellation;
    private boolean notificationEnabled;
//    private List<Accomodation> favourites;

    public void copyValues(Guest guest){
        this.setName(guest.getName());
        this.setLastName(guest.getLastName());
        this.setUser(guest.getUser());
        this.setAddress(guest.getAddress());
        this.setNotificationEnabled(guest.notificationEnabled);
        this.setNumberOfCancellation(guest.numberOfCancellation);
        this.setPhoneNumber(guest.getPhoneNumber());
        this.setUser(guest.getUser());
    }
}
