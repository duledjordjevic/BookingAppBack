package com.booking.project.service.interfaces;

import com.booking.project.dto.UserReportDTO;
import com.booking.project.model.UserReport;

import java.util.Collection;

public interface IUserReportService {
    UserReportDTO create(UserReportDTO userReportDTO) throws Exception;
}
