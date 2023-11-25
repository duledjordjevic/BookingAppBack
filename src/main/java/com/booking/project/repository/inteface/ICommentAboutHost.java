package com.booking.project.repository.inteface;

import com.booking.project.model.CommentAboutHost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentAboutHost extends JpaRepository<CommentAboutHost,Long> {
}
