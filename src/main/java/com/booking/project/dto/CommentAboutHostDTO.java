package com.booking.project.dto;

import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentAboutHostDTO {

    private Long id;
    private int rating;
    private boolean isReported;
    private String content;
    private boolean isApproved;

    public CommentAboutHostDTO(CommentAboutHost commentAboutHost){
        this.id = commentAboutHost.getId();
        this.rating = commentAboutHost.getRating();
        this.isReported = commentAboutHost.isReported();
        this.content = commentAboutHost.getContent();
        this.isApproved = commentAboutHost.isApproved();
    }
}
