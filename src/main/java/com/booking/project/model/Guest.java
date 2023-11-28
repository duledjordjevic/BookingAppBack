package com.booking.project.model;


import com.booking.project.dto.GuestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Accommodation> favourites = new HashSet<>();

    public Guest(GuestDTO guestDTO){
        this.setId(guestDTO.getId());
        this.setName(guestDTO.getName());
        this.setLastName(guestDTO.getLastName());
        this.setAddress(guestDTO.getAddress());
        this.setPhoneNumber(guestDTO.getPhoneNumber());
        this.setNotificationEnabled(guestDTO.isNotificationEnabled());
        User user = new User(guestDTO.getUserCredentialsDTO());
        this.setUser(user);
    }

    public void copyValues(GuestDTO guestDTO){
        this.setId(guestDTO.getId());
        this.setName(guestDTO.getName());
        this.setLastName(guestDTO.getLastName());
        this.setAddress(guestDTO.getAddress());
        this.setPhoneNumber(guestDTO.getPhoneNumber());
        this.setNotificationEnabled(guestDTO.isNotificationEnabled());
        User user = new User(guestDTO.getUserCredentialsDTO());
        this.setUser(user);
    }

}
