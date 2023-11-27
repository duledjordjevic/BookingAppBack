package com.booking.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentAboutAccDTO {
    private int rating;
    private String content;
    private Long guestId;
    private Long hostId;
}
