package com.booking.project.dto;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Address;
import com.booking.project.model.Guest;
import com.booking.project.model.User;
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
public class GuestDTO {

    private Long id;
    private String name;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private boolean isNotificationEnabled;
    private UserCredentialsDTO userCredentialsDTO;
    private GuestDTO(Guest guest){
        this.id = guest.getId();
        this.name = guest.getName();
        this.lastName = guest.getLastName();
        this.address = guest.getAddress();
        this.phoneNumber = guest.getPhoneNumber();
        this.isNotificationEnabled = guest.isNotificationEnabled();
        this.userCredentialsDTO = new UserCredentialsDTO(guest.getUser());
    }
}
