package com.booking.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private int rating;
    private boolean isReported;
    private String content;
    private boolean isApproved;
}
