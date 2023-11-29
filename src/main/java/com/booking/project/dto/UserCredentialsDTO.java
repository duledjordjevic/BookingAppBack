package com.booking.project.dto;

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
public class UserCredentialsDTO {
    private Long id;
    private String email;
    private String password;
    private UserType userType;

    public UserCredentialsDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }
}
