package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.CommentAboutAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutAccRepository extends JpaRepository<CommentAboutAcc,Long> {
    Collection<CommentAboutAcc> findAllByAccommodation(Optional<Accommodation> accommodation);
    @Query("select c from CommentAboutAcc c where c.isReported = true")
    Collection<CommentAboutAcc> findByReportedTrue();
}
