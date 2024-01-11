package com.booking.project.repository.inteface;

import com.booking.project.model.User;
import com.booking.project.model.UserReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserReportRepository extends JpaRepository<UserReport, Long> {
    @Modifying
    @Transactional
    @Query("delete from UserReport ur " +
            "where ur.reportedUser.id = :id")
    void deleteUserReportAfterBlock(@Param("id") Long id);
}
