package com.booking.project.repository.inteface;

import com.booking.project.model.CommentAboutAcc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentAboutAcc extends JpaRepository<CommentAboutAcc,Long> {
}
