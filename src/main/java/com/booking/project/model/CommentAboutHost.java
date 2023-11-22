package com.booking.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentAboutHost extends Comment{
    private Guest guest;
    private Host host;
}
