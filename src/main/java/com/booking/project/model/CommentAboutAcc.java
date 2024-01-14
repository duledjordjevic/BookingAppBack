package com.booking.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="comments_about_accommodaitons")
public class CommentAboutAcc  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private boolean isReported;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isApproved;

    @ManyToOne(fetch = FetchType.EAGER)
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.EAGER)
    private Guest guest;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String reportMessage;

}
