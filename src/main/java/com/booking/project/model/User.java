package com.booking.project.model;

import com.booking.project.model.enums.UserStatus;
import com.booking.project.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = false)
    private boolean isReported;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public void copyValues(User user){
        this.email = user.email;
        this.password = user.password;
    }
}
