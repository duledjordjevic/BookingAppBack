package com.booking.project.repository.inteface;

import com.booking.project.model.CommentAboutHost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentAboutHostRepository extends JpaRepository<CommentAboutHost,Long> {
}
