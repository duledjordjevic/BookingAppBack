package com.booking.project.dto;

import com.booking.project.model.Address;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private Long id;
    private String email;
    private String password;
    private Address address;
    private String phoneNumber;
    private UserType userType;
    private String name;
    private String lastname;
    public UserInfoDTO(Guest guest, User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.address = guest.getAddress();
        this.phoneNumber = guest.getPhoneNumber();
        this.userType = user.getUserType();
        this.name = guest.getName();
        this.lastname = guest.getLastName();
    }
    public UserInfoDTO(Host host, User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.address = host.getAddress();
        this.phoneNumber = host.getPhoneNumber();
        this.userType = user.getUserType();
        this.name = host.getName();
        this.lastname = host.getLastName();
    }
}
