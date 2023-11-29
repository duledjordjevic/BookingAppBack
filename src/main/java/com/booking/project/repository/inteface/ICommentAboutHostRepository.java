package com.booking.project.repository.inteface;

import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.NotificationForGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutHostRepository extends JpaRepository<CommentAboutHost,Long> {

    Collection<CommentAboutHost> findAllByHost(Optional<Host> host);
    @Query("select c from CommentAboutHost c where c.isReported = true")
    Collection<CommentAboutHost> findByReportedTrue();
}
