package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.CommentAboutAcc;
import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.enums.AccommodationApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICommentAboutAccRepository extends JpaRepository<CommentAboutAcc,Long> {
    Collection<CommentAboutAcc> findAllByAccommodation(Optional<Accommodation> accommodation);
    @Query("select c from CommentAboutAcc c where c.isReported = true")
    Collection<CommentAboutAcc> findByReportedTrue();
    @Query("select distinct a,avg(c.rating) " +
            "from CommentAboutAcc  c " +
            "join c.accommodation a " +
            "where a.accommodationApprovalStatus= :approved " +
            "group by a")
    Collection<Object[]> findAccomodationByRating(@Param("approved") AccommodationApprovalStatus approved);

    @Query("select avg(c.rating) " +
            "from CommentAboutAcc c " +
            "where c.accommodation.id =:accommodationId " +
            "and c.isApproved = true " +
            "and c.isReported = false")
    Double findAvgRateByAccommodation(@Param("accommodationId") Long accommodationId);
    @Query("select c from" +
            " CommentAboutAcc c where" +
            " c.guest.user.id = :id")
    List<CommentAboutAcc> findByGuestUser(Long id);

    @Query("SELECT c FROM CommentAboutAcc c " +
            "WHERE c.accommodation.id = :accommodationId " +
            "AND c.isApproved = true")
    Collection<CommentAboutAcc> findAllForDisplay(@Param("accommodationId") Long accommodationId);
}
