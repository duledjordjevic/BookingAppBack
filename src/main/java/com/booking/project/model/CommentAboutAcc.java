package com.booking.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentAboutAcc extends Comment {
    private Accomodation accomodation;
    private Guest guest;
}
