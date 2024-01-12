package com.booking.project.dto;

import com.booking.project.model.User;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String oldPassword;

    @NotEmpty
    private String newPassword;
    public UserAdminUpdateDTO(User user){
        this.email = user.getEmail();
    }

}
