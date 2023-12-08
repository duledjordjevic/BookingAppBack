package com.booking.project.dto;

import com.booking.project.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private Long id;
    private String email;
    private Address address;
    private String phoneNumber;
    private String name;
    private String lastname;
    private String oldPassword;
    private String newPassword;
}
