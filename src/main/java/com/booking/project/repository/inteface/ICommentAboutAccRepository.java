package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.CommentAboutAcc;
import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutAccRepository extends JpaRepository<CommentAboutAcc,Long> {
    Collection<CommentAboutAcc> findAllByAccommodation(Optional<Accommodation> accommodation);
}
