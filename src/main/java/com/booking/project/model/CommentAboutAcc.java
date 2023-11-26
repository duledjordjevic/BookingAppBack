package com.booking.project.model;

import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.dto.UserCredentialsDTO;
import com.booking.project.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest guest;
}
