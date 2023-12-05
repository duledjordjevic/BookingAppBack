package com.booking.project.dto;

import com.booking.project.model.CommentAboutAcc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentAboutAccDTO {

    private Long id;
    private int rating;
    private boolean isReported;
    private String content;
    private boolean isApproved;
    private GuestDTO guestDTO;

    public CommentAboutAccDTO(CommentAboutAcc commentAboutAcc){
        this.id = commentAboutAcc.getId();
        this.rating = commentAboutAcc.getRating();
        this.isReported = commentAboutAcc.isReported();
        this.content = commentAboutAcc.getContent();
        this.isApproved = commentAboutAcc.isApproved();
        this.guestDTO = new GuestDTO(commentAboutAcc.getGuest());
    }
}
