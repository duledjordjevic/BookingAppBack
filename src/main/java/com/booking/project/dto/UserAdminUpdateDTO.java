package com.booking.project.dto;

import com.booking.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminUpdateDTO {
    private Long id;
    private String email;
    private String oldPassword;
    private String newPassword;
    public UserAdminUpdateDTO(User user){
        this.email = user.getEmail();
    }

}
