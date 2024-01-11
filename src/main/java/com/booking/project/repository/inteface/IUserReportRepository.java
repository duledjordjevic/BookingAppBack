package com.booking.project.repository.inteface;

import com.booking.project.model.User;
import com.booking.project.model.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserReportRepository extends JpaRepository<UserReport, Long> {
}
