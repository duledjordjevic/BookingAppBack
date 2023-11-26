package com.booking.project.model;

import com.booking.project.dto.HostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hosts")
public class Host extends Person{
    public Host(HostDTO hostDTO){
        this.setId(hostDTO.getId());
        this.setName(hostDTO.getName());
        this.setLastName(hostDTO.getLastName());
        this.setAddress(hostDTO.getAddress());
        this.setPhoneNumber(hostDTO.getPhoneNumber());
        this.setNotificationEnabled(hostDTO.isNotificationEnabled());
        User user = new User(hostDTO.getUserCredentialsDTO());
        this.setUser(user);
    }

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

