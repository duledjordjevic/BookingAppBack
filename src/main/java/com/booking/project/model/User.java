package com.booking.project.model;

import com.booking.project.model.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private UserType userType;
}
