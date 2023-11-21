package com.booking.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {

    private Long id;
    private String street;
    private String city;
    private String state;
    private int postalCode;

}
