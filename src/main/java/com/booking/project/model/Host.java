package com.booking.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "hosts")
public class Host extends Person{

    @OneToMany(fetch = FetchType.LAZY)
    private List<Accommodation> accommodations;
}
