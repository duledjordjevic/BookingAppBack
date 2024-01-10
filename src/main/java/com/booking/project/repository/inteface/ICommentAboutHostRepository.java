package com.booking.project.repository.inteface;

import com.booking.project.model.*;
import com.booking.project.model.enums.AccommodationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICommentAboutHostRepository extends JpaRepository<CommentAboutHost,Long> {

    Collection<CommentAboutHost> findAllByHost(Host host);
    @Query("select c from CommentAboutHost c where c.isReported = true")
    Collection<CommentAboutHost> findByReportedTrue();

    Collection<CommentAboutHost> findAllByGuest(Optional<Guest> guest);

    @Query("select c from" +
            " CommentAboutHost c where" +
            " c.guest.user.id = :id")
    List<CommentAboutHost> findByGuestUser(Long id);
}
