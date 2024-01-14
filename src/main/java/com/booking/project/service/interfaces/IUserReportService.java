package com.booking.project.service.interfaces;

import com.booking.project.dto.UserBlockDTO;
import com.booking.project.dto.UserDTO;
import com.booking.project.dto.UserReportDTO;
import com.booking.project.model.UserReport;

import java.util.Collection;

public interface IUserReportService {
    Collection<UserBlockDTO> findAll();

    UserReportDTO create(UserReportDTO userReportDTO) throws Exception;

    void deleteUserReports(Long id);

    UserDTO blockUser(Long id);
}
