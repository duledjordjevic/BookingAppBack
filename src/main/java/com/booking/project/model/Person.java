package com.booking.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    private String lastName;
    @OneToOne(cascade = { CascadeType.ALL })
    private Address address;
    private String phoneNumber;
    @OneToOne(cascade = { CascadeType.ALL })
    private User user;

}
