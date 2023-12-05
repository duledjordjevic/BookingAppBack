package com.booking.project.model;

import com.booking.project.dto.HostDTO;
import com.booking.project.dto.UserRegisterDTO;
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
    public Host(UserRegisterDTO userRegisterDTO){
        this.setName(userRegisterDTO.getName());
        this.setLastName(userRegisterDTO.getLastname());
        this.setAddress(userRegisterDTO.getAddress());
        this.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        this.setNotificationEnabled(true);
    }
}

