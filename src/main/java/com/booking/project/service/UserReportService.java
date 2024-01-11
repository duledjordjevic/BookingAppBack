package com.booking.project.service;

import com.booking.project.dto.UserReportDTO;
import com.booking.project.model.Reservation;
import com.booking.project.model.User;
import com.booking.project.model.UserReport;
import com.booking.project.model.enums.UserType;
import com.booking.project.repository.inteface.IUserReportRepository;
import com.booking.project.service.interfaces.IReservationService;
import com.booking.project.service.interfaces.IUserReportService;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserReportService implements IUserReportService {
    @Autowired
    private IUserReportRepository userReportRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IReservationService reservationService;
    
    public Collection<UserReport> findAll(){
        return userReportRepository.findAll();
    }
    @Override
    public UserReportDTO create(UserReportDTO userReportDTO) throws Exception {
        UserReport userReport = new UserReport();
        Optional<User> user = userService.findById(userReportDTO.getUserId());
        Optional<Reservation> reservation = reservationService.findById(userReportDTO.getReservationId());

        if(user.isEmpty()){
            return null;
        }
        if(reservation.isEmpty()){
            return null;
        }
        if(user.get().getUserType().equals(UserType.GUEST)){
            reservation.get().setGuestReported(true);
        }else if(user.get().getUserType().equals(UserType.HOST)) {
            reservation.get().setHostReported(true);
        }
        reservationService.save(reservation.get());

        user.get().setReported(true);
        userService.save(user.get());

        userReport.setUser(user.get());
        userReport.setReason(userReportDTO.getReason());
        userReport.setReservation(reservation.get());

        userReportRepository.save(userReport);
        return userReportDTO;

    }

}
