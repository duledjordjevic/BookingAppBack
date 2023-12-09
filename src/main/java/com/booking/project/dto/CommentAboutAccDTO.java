package com.booking.project.dto;

import com.booking.project.model.CommentAboutAcc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentAboutAccDTO {

    private Long id;
    private int rating;
    private String content;
    private String guestName;
    private String guestLastName;
    private String guestEmail;
    private LocalDate date;

    public CommentAboutAccDTO(CommentAboutAcc commentAboutAcc){
        this.id = commentAboutAcc.getId();
        this.rating = commentAboutAcc.getRating();
        this.content = commentAboutAcc.getContent();
        this.guestEmail = commentAboutAcc.getGuest().getUser().getEmail();
        this.guestName = commentAboutAcc.getGuest().getName();
        this.guestLastName = commentAboutAcc.getGuest().getLastName();
        this.date = commentAboutAcc.getDate();
    }
}
