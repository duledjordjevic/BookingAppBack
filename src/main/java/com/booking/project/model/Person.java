package com.booking.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Person {

    private Long id;
    private String name;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private User user;

}
