package com.booking.project.model;

import com.booking.project.dto.HostDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "hosts")
public class Host extends Person{

    public void copyValues(HostDTO hostDTO){

        this.setId(hostDTO.getId());
        this.setName(hostDTO.getName());
        this.setLastName(hostDTO.getLastName());
        this.setAddress(hostDTO.getAddress());
        this.setPhoneNumber(hostDTO.getPhoneNumber());
        this.setNotificationEnabled(hostDTO.isNotificationEnabled());
        User user = new User(hostDTO.getUserCredentialsDTO());
        this.setUser(user);
    }
}
