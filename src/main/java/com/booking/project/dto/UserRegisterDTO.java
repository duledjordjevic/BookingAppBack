package com.booking.project.dto;

import com.booking.project.model.Address;
import com.booking.project.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private int id;
    private String email;
    private String password;
    private Address address;
    private String phoneNumber;
    private UserType userType;
    private String name;
    private String lastname;
}
