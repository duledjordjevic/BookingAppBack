package com.booking.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guests")
public class Guest extends Person {
    @Column(nullable = false)
    private int numberOfCancellation;

    @Column(nullable = false)
    private boolean notificationEnabled;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Accommodation> favourites = new HashSet<>();
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
