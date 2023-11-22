package com.booking.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guest extends Person {
    private int numberOfCancellation;
    private boolean notificationEnabled;
    private List<Accomodation> favourites;
}
